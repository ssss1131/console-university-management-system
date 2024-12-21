package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.comparator.CitationsComparator;
import main.java.kbtu.chill_guys.university_management_system.comparator.PublicationDateComparator;
import main.java.kbtu.chill_guys.university_management_system.comparator.TitleLengthComparator;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Format;
import main.java.kbtu.chill_guys.university_management_system.exception.InvalidFormatException;
import main.java.kbtu.chill_guys.university_management_system.exception.NotExistingComparatorException;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.storage.JournalStorage;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ResearcherViewEn implements ResearcherView {

    private final Scanner scanner = new Scanner(System.in);
    
    private final ResearcherService researcherService = ResearcherService.getInstance();
    private final JournalService service = new JournalService();
    
    private final JournalStorage storage = JournalStorage.getInstance();
    private final JournalView view = new JournalViewEn();
    
    public void displayInvalidUser() {
        System.out.println("you cant be researcher((");
    }

    public void displaySuccessBecomingResearcher() {
        System.out.println("now you are researcher!!");
    }

    public void displayAlreadyIsResearcher() {
        System.out.println("you are already a researcher\uD83E\uDD14");
    }

    public ResearchPaper getResearchPaper() {
        System.out.println("=== Create New Research Paper ===");

        System.out.println("Enter title of the research paper:");
        String title = InputValidatorUtil.validateNonEmptyInput("Title cannot be empty.");

        System.out.println("Enter thesis of the research paper:");
        String thesis = InputValidatorUtil.validateNonEmptyInput("Thesis cannot be empty.");

        System.out.println("Would you like to generate DOI automatically or enter it manually?");
        System.out.println("1. Generate automatically");
        System.out.println("2. Enter manually");
        int doiChoice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, 2);

        String doi;
        if (doiChoice == 1) {
            doi = generateDOI();
            System.out.println("Generated DOI: " + doi);
        } else {
            System.out.println("Enter DOI:");
            while (true) {
                doi = scanner.nextLine().trim();
                if (researcherService.isDOIUnique(doi)) {
                    break;
                } else {
                    System.out.println("DOI is not unique. Please enter a different DOI:");
                }
            }
        }

        Journal journal = selectJournal();

        System.out.println("Enter the number of citations:");
        Integer citations = InputValidatorUtil.validateIntegerInput("Citations must be a positive integer.", 0, Integer.MAX_VALUE);

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> authors = selectAuthors(allResearchers);
        authors.add(Menu.getInstance().getLoggedUser());

        System.out.println("Enter publication date (yyyy-MM-dd):");
        String publicationDate = InputValidatorUtil.validateDateInput("Invalid date format. Please try again.");

        ResearchPaper researchPaper = new ResearchPaper(title, thesis, journal, citations, doi, LocalDate.parse(publicationDate), authors);

        System.out.println("Research paper created successfully.");
        return researchPaper;
    }

    public Journal selectJournal() {
        System.out.println("Available journals:");
        Set<Journal> journals = storage.getPapersByJournal().keySet();

        if (journals.isEmpty()) {
            System.out.println("No journals available. Please create a new journal.");
            return createNewJournal();
        }

        int index = 1;
        List<Journal> journalList = new ArrayList<>(journals);
        for (Journal journal : journalList) {
            System.out.printf("%d. %s%n", index++, journal.getName());
        }

        System.out.println(index + ". Create a new journal");
        int choice = InputValidatorUtil.validateIntegerInput("Select a journal: ", 1, index);

        if (choice == index) {
            return createNewJournal();
        } else {
            return journalList.get(choice - 1);
        }
    }

    public Journal createNewJournal() {
        String journalName = view.getNewJournalName();
        return new Journal(journalName, UUID.randomUUID());
    }


    public String generateDOI() {
        return "10." + System.currentTimeMillis();
    }

    public Vector<User> selectAuthors(List<User> researchers) {
        Vector<User> selectedAuthors = new Vector<>();
        if (researchers.isEmpty()) {
            System.out.println("No researchers available to add as authors.");
            return selectedAuthors;
        }

        System.out.println("Select authors except you (enter numbers separated by commas):");
        for (int i = 0; i < researchers.size(); i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, researchers.get(i).getFirstName(), researchers.get(i).getLastName());
        }

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("No authors selected.");
                break;
            }

            try {
                String[] parts = input.split(",");
                for (String part : parts) {
                    int index = Integer.parseInt(part.trim()) - 1;
                    if (index >= 0 && index < researchers.size()) {
                        selectedAuthors.add(researchers.get(index));
                    } else {
                        System.out.println("Invalid number: " + (index + 1));
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers separated by commas.");
            }
        }
        return selectedAuthors;
    }

    @Override
    public void displayResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("\n=== No Research Papers Found ===");
            System.out.println("You have not authored any research papers yet.");
            return;
        }

        System.out.println("\n=== Your Research Papers ===");
        System.out.printf("%-5s %-35s %-25s %-12s %-35s %-15s%n",
                "No.", "Title", "Journal", "Citations", "DOI", "Publication Date");
        System.out.println("-".repeat(130));

        int index = 1;
        for (ResearchPaper paper : papers) {
            System.out.printf("%-5d %-35s %-25s %-12d %-35s %-25s%n",
                    index++,
                    paper.getTitle(),
                    paper.getJournal().getName(),
                    paper.getCitations(),
                    paper.getDoi(),
                    paper.getPublicationDate());
        }

        System.out.println("-".repeat(130));
    }

    @Override
    public void displayResearchProjects(List<ResearchProject> projects) {
        if (projects.isEmpty()) {
            System.out.println("\n=== No Research Projects Found ===");
            System.out.println("You are not participating in any research projects yet.");
            return;
        }

        System.out.println("\n=== Your Research Projects ===");
        System.out.printf("%-5s %-35s %-50s %-20s %-20s %-50s%n",
                "No.", "Title", "Description", "Start Date", "End Date", "Published Papers");
        System.out.println("-".repeat(180));

        int index = 1;
        for (ResearchProject project : projects) {
            String paperTitles = project.getPublishedPapers().isEmpty()
                    ? "No papers"
                    : String.join(", ", project.getPublishedPapers().stream()
                    .map(ResearchPaper::getTitle)
                    .toList());

            System.out.printf("%-5d %-35s %-50s %-20s %-20s %-50s%n",
                    index++,
                    project.getTitle(),
                    truncateText(project.getDescription(), 50),
                    project.getStartDate().toLocalDate(),
                    project.getEndDate().toLocalDate(),
                    truncateText(paperTitles, 70));
        }

        System.out.println("-".repeat(180));
    }

    public Comparator<ResearchPaper> getSortPreference() {
        System.out.println("Choose sorting preference:");
        System.out.println("1. By Publication Date");
        System.out.println("2. By Citations");
        System.out.println("3. By Title Length");

        int choice = InputValidatorUtil.validateIntegerInput("Enter valid number:  ", 1, 3);

        switch (choice) {
            case 1 -> {
                return new PublicationDateComparator();
            }
            case 2 -> {
                return new CitationsComparator();
            }
            case 3 -> {
                return new TitleLengthComparator();
            }
            default -> throw new NotExistingComparatorException("Invalid choice!");
        }
    }

    @Override
    public School getSchool() {
        System.out.println("Please write school:");
        return EnumSelectionUtil.selectEnum(School.class);
    }

    @Override
    public void showTopCitedResearcher(User user, int totalCitations, int year) {
        if (user == null) {
            System.out.println("No top-cited researcher found.");
            return;
        }

        System.out.println("\n=== Top-Cited Researcher ===");
        System.out.printf("%-20s: %s %s%n", "Name", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %d%n", "Total Citations", totalCitations);
        System.out.printf("%-20s: %d%n", "Year", year);
        System.out.println("=".repeat(50));
    }

    @Override
    public void showTopCitedResearcherOfSchool(User user, School school, int totalCitations) {
        if (user == null) {
            System.out.println("No top-cited researcher found for school: " + school);
            return;
        }

        System.out.println("\n=== Top-Cited Researcher of School ===");
        System.out.printf("%-20s: %s %s%n", "Name", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %s%n", "School", school);
        System.out.printf("%-20s: %d%n", "Total Citations", totalCitations);
        System.out.println("=".repeat(50));
    }




    @Override
    public int selectPublicationYear(List<Integer> years) {
        if (years.isEmpty()) {
            System.out.println("No publication years available.");
            return -1;
        }

        System.out.println("Available publication years:");
        for (int i = 0; i < years.size(); i++) {
            System.out.printf("%d. %d%n", i + 1, years.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput(
                "Select a year by its number: ", 1, years.size());

        return years.get(choice - 1);
    }



    private String truncateText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }



    @Override
    public ResearchProject getResearchProject() {
        System.out.println("=== Create New Research Project ===");

        System.out.println("Enter the title of the research project:");
        String title = InputValidatorUtil.validateNonEmptyInput("Title cannot be empty.");

        System.out.println("Enter the description of the research project:");
        String description = InputValidatorUtil.validateNonEmptyInput("Description cannot be empty.");


        Journal journal = selectJournal();

        System.out.println("Enter the start date of the project (yyyy-MM-dd):");
        String startDateString = InputValidatorUtil.validateDateInput("Invalid date format. Please try again.");
        LocalDateTime startDate = LocalDateTime.parse(startDateString + "T00:00:00");

        System.out.println("Enter the end date of the project (yyyy-MM-dd):");
        String endDateString = InputValidatorUtil.validateDateInput("Invalid date format. Please try again.", LocalDate.parse(startDateString));
        LocalDateTime endDate = LocalDateTime.parse(endDateString + "T23:59:59");

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> participants = selectAuthors(allResearchers);
        participants.add(Menu.getInstance().getLoggedUser());

        Vector<ResearchPaper> papers = selectResearchPapers(participants);

        ResearchProject project = new ResearchProject(title, description, journal, startDate, endDate, participants, papers);

        System.out.println("Research project created successfully.");
        return project;
    }

    public Vector<ResearchPaper> selectResearchPapers(Vector<User> participants) {
        Vector<ResearchPaper> selectedPapers = new Vector<>();

        System.out.println("Selecting research papers for the project:");
        for (User participant : participants) {
            List<ResearchPaper> papers = researcherService.getResearchPapers(participant);
            if (papers.isEmpty()) {
                System.out.println("No research papers available for participant: " + participant.getFirstName() + " " + participant.getLastName());
                continue;
            }

            System.out.println("Research papers by " + participant.getFirstName() + " " + participant.getLastName() + ":");
            for (int i = 0; i < papers.size(); i++) {
                System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
            }

            System.out.println("Select paper numbers (comma-separated) or press Enter to skip:");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    String[] indices = input.split(",");
                    for (String index : indices) {
                        int paperIndex = Integer.parseInt(index.trim()) - 1;
                        if (paperIndex >= 0 && paperIndex < papers.size()) {
                            selectedPapers.add(papers.get(paperIndex));
                        } else {
                            System.out.println("Invalid paper number: " + (paperIndex + 1));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Skipping selection for this participant.");
                }
            }
        }

        return selectedPapers;
    }

    @Override
    public ResearchPaper selectResearchPaper(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("No research papers available.");
            return null;
        }

        System.out.println("Available Research Papers:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, papers.get(i).getTitle());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Select a research paper by its number: ", 1, papers.size());
        return papers.get(choice - 1);
    }

    @Override
    public Format selectCitationFormat() {
        System.out.println("Select citation format:");
        System.out.println("1. Plain Text");
        System.out.println("2. BibTeX");

        int choice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, 2);

        return switch (choice) {
            case 1 -> Format.PLAIN_TEXT;
            case 2 -> Format.BIBTEX;
            default -> throw new InvalidFormatException("Invalid choice!");
        };
    }


    @Override
    public void displayCitation(ResearchPaper paper, Format format) {
        System.out.println("\n=== Citation ===");

        String citation = switch (format) {
            case PLAIN_TEXT -> String.format("%s. %s. %s. DOI: %s. Published on: %s.",
                    paper.getAuthorsAsString(),
                    paper.getTitle(),
                    paper.getJournal().getName(),
                    paper.getDoi(),
                    paper.getPublicationDate());
            case BIBTEX -> String.format("""
                @article{%s,
                    author = {%s},
                    title = {%s},
                    journal = {%s},
                    year = {%d},
                    doi = {%s}
                }
                """,
                    paper.getDoi(),
                    paper.getAuthorsAsString(),
                    paper.getTitle(),
                    paper.getJournal().getName(),
                    paper.getPublicationDate().getYear(),
                    paper.getDoi());
        };

        System.out.println(citation);
        System.out.println("=".repeat(50));
    }



}
