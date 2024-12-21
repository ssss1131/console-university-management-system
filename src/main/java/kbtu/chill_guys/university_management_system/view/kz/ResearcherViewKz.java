package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.storage.JournalStorage;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

import java.time.LocalDate;
import java.util.*;

public class ResearcherViewKz implements ResearcherView {

    private final Scanner scanner = new Scanner(System.in);
    private final ResearcherService researcherService = ResearcherService.getInstance();
    private final JournalService service = new JournalService();
    private final JournalStorage storage = JournalStorage.getInstance();
    private final JournalView view = new JournalViewKz();

    public void displayInvalidUser() {
        System.out.println("Сіз зерттеуші бола алмайсыз((");
    }

    public void displaySuccessBecomingResearcher() {
        System.out.println("Енді сіз зерттеушісіз!!");
    }

    public void displayAlreadyIsResearcher() {
        System.out.println("Сіз қазірдің өзінде зерттеушісіз\uD83E\uDD14");
    }

    public ResearchPaper getResearchPaper() {
        System.out.println("=== Жаңа ғылыми жұмыс жасау ===");

        System.out.println("Ғылыми жұмыстың атауын енгізіңіз:");
        String title = InputValidatorUtil.validateNonEmptyInput("Атау бос болмауы керек.");

        System.out.println("Ғылыми жұмыстың тезисін енгізіңіз:");
        String thesis = InputValidatorUtil.validateNonEmptyInput("Тезис бос болмауы керек.");

        System.out.println("DOI автоматты түрде жасалсын ба әлде қолмен енгізесіз бе?");
        System.out.println("1. Автоматты түрде жасау");
        System.out.println("2. Қолмен енгізу");
        int doiChoice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, 2);

        String doi;
        if (doiChoice == 1) {
            doi = generateDOI();
            System.out.println("Жасалған DOI: " + doi);
        } else {
            System.out.println("DOI енгізіңіз:");
            while (true) {
                doi = scanner.nextLine().trim();
                if (researcherService.isDOIUnique(doi)) {
                    break;
                } else {
                    System.out.println("DOI бірегей емес. Басқа DOI енгізіңіз:");
                }
            }
        }

        Journal journal = selectJournal();

        System.out.println("Дәйексөздер санын енгізіңіз:");
        Integer citations = InputValidatorUtil.validateIntegerInput("Дәйексөздер саны оң бүтін сан болуы керек.", 0, Integer.MAX_VALUE);

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> authors = selectAuthors(allResearchers);

        System.out.println("Жарияланған күнін енгізіңіз (yyyy-MM-dd):");
        String publicationDate = InputValidatorUtil.validateDateInput("Күн форматы дұрыс емес. Қайта енгізіңіз.");

        ResearchPaper researchPaper = new ResearchPaper(title, thesis, journal, citations, doi, LocalDate.parse(publicationDate), authors);

        System.out.println("Ғылыми жұмыс сәтті жасалды.");
        return researchPaper;
    }

    public Journal selectJournal() {
        System.out.println("Қолжетімді журналдар:");
        Set<Journal> journals = storage.getPapersByJournal().keySet();

        if (journals.isEmpty()) {
            System.out.println("Журналдар жоқ. Жаңа журнал жасаңыз.");
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

    public String generateDOI() {
        return "10." + System.currentTimeMillis();
    }

    public Vector<User> selectAuthors(List<User> researchers) {
        Vector<User> selectedAuthors = new Vector<>();
        if (researchers.isEmpty()) {
            System.out.println("Автор ретінде қосуға зерттеушілер жоқ.");
            return selectedAuthors;
        }

        System.out.println("Өзіңізден басқа авторларды таңдаңыз (нөмірлерін үтір арқылы енгізіңіз):");
        for (int i = 0; i < researchers.size(); i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, researchers.get(i).getFirstName(), researchers.get(i).getLastName());
        }

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Авторлар таңдалмады.");
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
                System.out.println("Жарамсыз енгізу. Нөмірлерді үтір арқылы енгізіңіз.");
            }
        }
        return selectedAuthors;
    }

    @Override
    public void displayResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("\n=== Ғылыми жұмыстар табылмады ===");
            System.out.println("Сіз әлі ғылыми жұмыс авторы емессіз.");
            return;
        }

        System.out.println("\n=== Сіздің ғылыми жұмыстарыңыз ===");
        System.out.printf("%-5s %-30s %-15s %-10s %-15s %-20s%n",
                "№", "Атауы", "Журнал", "Дәйексөздер", "DOI", "Жарияланған күні");
        System.out.println("----------------------------------------------------------------------------");

        int index = 1;
        for (ResearchPaper paper : papers) {
            System.out.printf("%-5d %-30s %-15s %-10d %-15s %-20s%n",
                    index++,
                    paper.getTitle(),
                    paper.getJournal().getName(),
                    paper.getCitations(),
                    paper.getDoi(),
                    paper.getPublicationDate());
        }

        System.out.println("----------------------------------------------------------------------------");
    }

    @Override
    public ResearchProject getResearchProject() {
        return null;
    }

    @Override
    public Vector<ResearchPaper> selectResearchPapers(Vector<User> participants) {
        return null;
    }

    @Override
    public void displayResearchProjects(List<ResearchProject> projects) {

    }

    @Override
    public Comparator<ResearchPaper> getSortPreference() {
        return null;
    }

    @Override
    public School getSchool() {
        return null;
    }


}
