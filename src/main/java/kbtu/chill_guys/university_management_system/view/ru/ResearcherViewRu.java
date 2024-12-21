package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Format;
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

public class ResearcherViewRu implements ResearcherView {

    private final Scanner scanner = new Scanner(System.in);
    private final ResearcherService researcherService = ResearcherService.getInstance();
    private final JournalService service = new JournalService();
    private final JournalStorage storage = JournalStorage.getInstance();
    private final JournalView view = new JournalViewRu();

    public void displayInvalidUser() {
        System.out.println("Вы не можете быть исследователем((");
    }

    public void displaySuccessBecomingResearcher() {
        System.out.println("Теперь вы исследователь!!");
    }

    public void displayAlreadyIsResearcher() {
        System.out.println("Вы уже исследователь\uD83E\uDD14");
    }

    public ResearchPaper getResearchPaper() {
        System.out.println("=== Создать новую научную работу ===");

        System.out.println("Введите название научной работы:");
        String title = InputValidatorUtil.validateNonEmptyInput("Название не может быть пустым.");

        System.out.println("Введите тезис научной работы:");
        String thesis = InputValidatorUtil.validateNonEmptyInput("Тезис не может быть пустым.");

        System.out.println("Вы хотите автоматически сгенерировать DOI или ввести его вручную?");
        System.out.println("1. Сгенерировать автоматически");
        System.out.println("2. Ввести вручную");
        int doiChoice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, 2);

        String doi;
        if (doiChoice == 1) {
            doi = generateDOI();
            System.out.println("Сгенерированный DOI: " + doi);
        } else {
            System.out.println("Введите DOI:");
            while (true) {
                doi = scanner.nextLine().trim();
                if (researcherService.isDOIUnique(doi)) {
                    break;
                } else {
                    System.out.println("DOI не уникален. Пожалуйста, введите другой DOI:");
                }
            }
        }

        Journal journal = selectJournal();

        System.out.println("Введите количество цитирований:");
        Integer citations = InputValidatorUtil.validateIntegerInput("Количество цитирований должно быть положительным числом.", 0, Integer.MAX_VALUE);

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> authors = selectAuthors(allResearchers);

        System.out.println("Введите дату публикации (yyyy-MM-dd):");
        String publicationDate = InputValidatorUtil.validateDateInput("Неверный формат даты. Попробуйте снова.");

        ResearchPaper researchPaper = new ResearchPaper(title, thesis, journal, citations, doi, LocalDate.parse(publicationDate), authors);

        System.out.println("Научная работа успешно создана.");
        return researchPaper;
    }

    public Journal selectJournal() {
        System.out.println("Доступные журналы:");
        Set<Journal> journals = storage.getPapersByJournal().keySet();

        if (journals.isEmpty()) {
            System.out.println("Журналы отсутствуют. Пожалуйста, создайте новый журнал.");
            return createNewJournal();
        }

        int index = 1;
        List<Journal> journalList = new ArrayList<>(journals);
        for (Journal journal : journalList) {
            System.out.printf("%d. %s%n", index++, journal.getName());
        }

        System.out.println(index + ". Создать новый журнал");
        int choice = InputValidatorUtil.validateIntegerInput("Выберите журнал: ", 1, index);

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
            System.out.println("Нет исследователей для добавления в качестве авторов.");
            return selectedAuthors;
        }

        System.out.println("Выберите авторов, кроме вас (введите номера через запятую):");
        for (int i = 0; i < researchers.size(); i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, researchers.get(i).getFirstName(), researchers.get(i).getLastName());
        }

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Авторы не выбраны.");
                break;
            }

            try {
                String[] parts = input.split(",");
                for (String part : parts) {
                    int index = Integer.parseInt(part.trim()) - 1;
                    if (index >= 0 && index < researchers.size()) {
                        selectedAuthors.add(researchers.get(index));
                    } else {
                        System.out.println("Неверный номер: " + (index + 1));
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите номера через запятую.");
            }
        }
        return selectedAuthors;
    }

    @Override
    public void displayResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("\n=== Научные работы не найдены ===");
            System.out.println("Вы ещё не являетесь автором научных работ.");
            return;
        }

        System.out.println("\n=== Ваши научные работы ===");
        System.out.printf("%-5s %-30s %-15s %-10s %-15s %-20s%n",
                "№", "Название", "Журнал", "Цитирования", "DOI", "Дата публикации");
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

    @Override
    public void showTopCitedResearcher(User user, int totalCitations, int year) {

    }

    @Override
    public void showTopCitedResearcherOfSchool(User user, School school, int totalCitations) {

    }


    @Override
    public int selectPublicationYear(List<Integer> years) {
        return 0;
    }

    @Override
    public ResearchPaper selectResearchPaper(List<ResearchPaper> papers) {
        return null;
    }

    @Override
    public Format selectCitationFormat() {
        return null;
    }

    @Override
    public void displayCitation(ResearchPaper paper, Format format) {

    }


}

