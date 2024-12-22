package main.java.kbtu.chill_guys.university_management_system.view.kz;

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

public class ResearcherViewKz implements ResearcherView {

    private final Scanner scanner = new Scanner(System.in);
    private final ResearcherService researcherService = ResearcherService.getInstance();
    private final JournalService service = new JournalService();
    private final JournalStorage storage = JournalStorage.getInstance();
    private final JournalView view = new JournalViewKz();

    @Override
    public void displayInvalidUser() {
        System.out.println("Сіз зерттеуші бола алмайсыз.");
    }

    @Override
    public void displaySuccessBecomingResearcher() {
        System.out.println("Сіз енді зерттеушісіз!");
    }

    @Override
    public void displayAlreadyIsResearcher() {
        System.out.println("Сіз қазірдің өзінде зерттеушісіз.");
    }

    @Override
    public ResearchPaper getResearchPaper() {
        System.out.println("=== Жаңа ғылыми мақала құру ===");

        System.out.println("Ғылыми мақала атауын енгізіңіз:");
        String title = InputValidatorUtil.validateNonEmptyInput("Атауы бос болмауы керек.");

        System.out.println("Мақала тезисін енгізіңіз:");
        String thesis = InputValidatorUtil.validateNonEmptyInput("Тезис бос болмауы керек.");

        System.out.println("DOI-ді автоматты түрде жасағыңыз келе ме немесе қолмен енгізесіз бе?");
        System.out.println("1. Автоматты түрде жасау");
        System.out.println("2. Қолмен енгізу");
        int doiChoice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, 2);

        String doi;
        if (doiChoice == 1) {
            doi = generateDOI();
            System.out.println("Жасалған DOI: " + doi);
        } else {
            System.out.println("DOI-ді енгізіңіз:");
            while (true) {
                doi = scanner.nextLine().trim();
                if (researcherService.isDOIUnique(doi)) {
                    break;
                } else {
                    System.out.println("DOI қайталанған. Басқа DOI енгізіңіз:");
                }
            }
        }

        Journal journal = selectJournal();

        System.out.println("Цитаталар санын енгізіңіз:");
        Integer citations = InputValidatorUtil.validateIntegerInput("Цитаталар саны оң сан болуы керек.", 0, Integer.MAX_VALUE);

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> authors = selectAuthors(allResearchers);
        authors.add(Menu.getInstance().getLoggedUser());

        System.out.println("Жариялау күнін енгізіңіз (yyyy-MM-dd):");
        String publicationDate = InputValidatorUtil.validateDateInput("Күн форматы дұрыс емес. Қайталап енгізіңіз.");

        ResearchPaper researchPaper = new ResearchPaper(title, thesis, journal, citations, doi, LocalDate.parse(publicationDate), authors);

        System.out.println("Ғылыми мақала сәтті құрылды.");
        return researchPaper;
    }

    @Override
    public String generateDOI() {
        return "10." + System.currentTimeMillis();
    }

    public Journal selectJournal() {
        System.out.println("Қолжетімді журналдар:");
        Set<Journal> journals = storage.getPapersByJournal().keySet();

        if (journals.isEmpty()) {
            System.out.println("Қолжетімді журналдар жоқ. Жаңа журнал жасаңыз.");
            return createNewJournal();
        }

        int index = 1;
        List<Journal> journalList = new ArrayList<>(journals);
        for (Journal journal : journalList) {
            System.out.printf("%d. %s%n", index++, journal.getName());
        }

        System.out.println(index + ". Жаңа журнал жасау");
        int choice = InputValidatorUtil.validateIntegerInput("Журналды таңдаңыз: ", 1, index);

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


    @Override
    public Vector<User> selectAuthors(List<User> researchers) {
        Vector<User> selectedAuthors = new Vector<>();
        if (researchers.isEmpty()) {
            System.out.println("Қол жетімді авторлар жоқ.");
            return selectedAuthors;
        }

        System.out.println("Авторларды таңдаңыз (сандарды үтір арқылы енгізіңіз):");
        for (int i = 0; i < researchers.size(); i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, researchers.get(i).getFirstName(), researchers.get(i).getLastName());
        }

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Авторлар таңдалған жоқ.");
                break;
            }

            try {
                String[] parts = input.split(",");
                for (String part : parts) {
                    int index = Integer.parseInt(part.trim()) - 1;
                    if (index >= 0 && index < researchers.size()) {
                        selectedAuthors.add(researchers.get(index));
                    } else {
                        System.out.println("Жарамсыз нөмір: " + (index + 1));
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Қате енгізу. Сандарды үтір арқылы енгізіңіз.");
            }
        }
        return selectedAuthors;
    }

    @Override
    public void displayResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("\n=== Ғылыми мақалалар жоқ ===");
            System.out.println("Сізде әлі бірде-бір ғылыми мақала жоқ.");
            return;
        }

        System.out.println("\n=== Сіздің ғылыми мақалаларыңыз ===");
        System.out.printf("%-5s %-35s %-25s %-12s %-35s %-15s%n",
                "No.", "Тақырыбы", "Журнал", "Сілтемелер саны", "DOI", "Жарияланған күні");
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
            System.out.println("\n=== Ғылыми жобалар жоқ ===");
            System.out.println("Сіз бірде-бір ғылыми жобаға қатыспадыңыз.");
            return;
        }

        System.out.println("\n=== Сіздің ғылыми жобаларыңыз ===");
        System.out.printf("%-5s %-35s %-50s %-20s %-20s %-50s%n",
                "No.", "Тақырыбы", "Сипаттама", "Басталуы", "Аяқталуы", "Жарияланған мақалалар");
        System.out.println("-".repeat(180));

        int index = 1;
        for (ResearchProject project : projects) {
            String paperTitles = project.getPublishedPapers().isEmpty()
                    ? "Мақалалар жоқ"
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
        System.out.println("Сұрыптаудың артықшылықты тәсілін таңдаңыз:");
        System.out.println("1. Жариялау күні бойынша");
        System.out.println("2. Сілтемелер саны бойынша");
        System.out.println("3. Тақырып ұзындығы бойынша");

        int choice = InputValidatorUtil.validateIntegerInput("Дұрыс нөмір енгізіңіз:  ", 1, 3);

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
            default -> throw new NotExistingComparatorException("Қате таңдау!");
        }
    }

    @Override
    public School getSchool() {
        System.out.println("Мектепті енгізіңіз:");
        return EnumSelectionUtil.selectEnum(School.class);
    }

    @Override
    public void showTopCitedResearcher(User user, int totalCitations, int year) {
        if (user == null) {
            System.out.println("Ең көп сілтеме жасаған зерттеуші жоқ.");
            return;
        }

        System.out.println("\n=== Ең көп сілтеме жасаған зерттеуші ===");
        System.out.printf("%-20s: %s %s%n", "Аты", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %d%n", "Жалпы сілтеме саны", totalCitations);
        System.out.printf("%-20s: %d%n", "Жыл", year);
        System.out.println("=".repeat(50));
    }


    @Override
    public void showTopCitedResearcherOfSchool(User user, School school, int totalCitations) {
        if (user == null) {
            System.out.println("Мектеп бойынша ең көп сілтеме жасаған зерттеуші табылған жоқ: " + school);
            return;
        }

        System.out.println("\n=== Мектеп бойынша ең көп сілтеме жасаған зерттеуші ===");
        System.out.printf("%-20s: %s %s%n", "Аты", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %s%n", "Мектеп", school);
        System.out.printf("%-20s: %d%n", "Жалпы сілтеме саны", totalCitations);
        System.out.println("=".repeat(50));
    }

    @Override
    public int selectPublicationYear(List<Integer> years) {
        if (years.isEmpty()) {
            System.out.println("Қолжетімді жариялау жылдары жоқ.");
            return -1;
        }

        System.out.println("Қолжетімді жариялау жылдары:");
        for (int i = 0; i < years.size(); i++) {
            System.out.printf("%d. %d%n", i + 1, years.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput(
                "Жыл нөмірін таңдап, оны таңдаңыз: ", 1, years.size());

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
        System.out.println("=== Жаңа зерттеу жобасын құру ===");

        System.out.println("Зерттеу жобасының атауын енгізіңіз:");
        String title = InputValidatorUtil.validateNonEmptyInput("Атау бос болмауы керек.");

        System.out.println("Зерттеу жобасының сипаттамасын енгізіңіз:");
        String description = InputValidatorUtil.validateNonEmptyInput("Сипаттама бос болмауы керек.");

        Journal journal = selectJournal();

        System.out.println("Жоба басталған күнді енгізіңіз (yyyy-MM-dd):");
        String startDateString = InputValidatorUtil.validateDateInput("Күн форматы дұрыс емес. Қайталап енгізіңіз.");
        LocalDateTime startDate = LocalDateTime.parse(startDateString + "T00:00:00");

        System.out.println("Жоба аяқталатын күнді енгізіңіз (yyyy-MM-dd):");
        String endDateString = InputValidatorUtil.validateDateInput("Күн форматы дұрыс емес. Қайталап енгізіңіз.", LocalDate.parse(startDateString));
        LocalDateTime endDate = LocalDateTime.parse(endDateString + "T23:59:59");

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> participants = selectAuthors(allResearchers);
        participants.add(Menu.getInstance().getLoggedUser());

        Vector<ResearchPaper> papers = selectResearchPapers(participants);

        ResearchProject project = new ResearchProject(title, description, journal, startDate, endDate, participants, papers);

        System.out.println("Зерттеу жобасы сәтті құрылды.");
        return project;
    }

    public Vector<ResearchPaper> selectResearchPapers(Vector<User> participants) {
        Vector<ResearchPaper> selectedPapers = new Vector<>();

        System.out.println("Жоба үшін зерттеу мақалаларын таңдау:");
        for (User participant : participants) {
            List<ResearchPaper> papers = researcherService.getResearchPapers(participant);
            if (papers.isEmpty()) {
                System.out.println("Қатысушы үшін зерттеу мақалалары жоқ: " + participant.getFirstName() + " " + participant.getLastName());
                continue;
            }

            System.out.println("Қатысушының зерттеу мақалалары " + participant.getFirstName() + " " + participant.getLastName() + ":");
            for (int i = 0; i < papers.size(); i++) {
                System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
            }

            System.out.println("Мақала нөмірлерін таңдаңыз (үтір арқылы енгізіңіз) немесе өткізіп жіберу үшін Enter пернесін басыңыз:");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    String[] indices = input.split(",");
                    for (String index : indices) {
                        int paperIndex = Integer.parseInt(index.trim()) - 1;
                        if (paperIndex >= 0 && paperIndex < papers.size()) {
                            selectedPapers.add(papers.get(paperIndex));
                        } else {
                            System.out.println("Жарамсыз мақала нөмірі: " + (paperIndex + 1));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Қате енгізу. Бұл қатысушы үшін мақаланы таңдауды өткізіп жіберу.");
                }
            }
        }

        return selectedPapers;
    }

    @Override
    public ResearchPaper selectResearchPaper(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("Қолжетімді зерттеу мақалалары жоқ.");
            return null;
        }

        System.out.println("Қолжетімді зерттеу мақалалары:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, papers.get(i).getTitle());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Мақаланың нөмірін таңдап, оны таңдаңыз: ", 1, papers.size());
        return papers.get(choice - 1);
    }

    @Override
    public Format selectCitationFormat() {
        System.out.println("Сілтеме форматтарын таңдаңыз:");
        System.out.println("1. Қарапайым мәтін");
        System.out.println("2. BibTeX");

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, 2);

        return switch (choice) {
            case 1 -> Format.PLAIN_TEXT;
            case 2 -> Format.BIBTEX;
            default -> throw new InvalidFormatException("Жарамсыз таңдау!");
        };
    }

    @Override
    public void displayCitation(ResearchPaper paper, Format format) {
        System.out.println("\n=== Сілтеме ===");

        String citation = switch (format) {
            case PLAIN_TEXT -> String.format("%s. %s. %s. DOI: %s. Жарияланған күні: %s.",
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
