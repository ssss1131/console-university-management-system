package main.java.kbtu.chill_guys.university_management_system.view.ru;

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
import main.java.kbtu.chill_guys.university_management_system.view.en.JournalViewEn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ResearcherViewRu implements ResearcherView {

    private final Scanner scanner = new Scanner(System.in);

    private final ResearcherService researcherService = ResearcherService.getInstance();

    private final JournalStorage storage = JournalStorage.getInstance();
    private final JournalView view = new JournalViewEn();

    public void displayInvalidUser() {
        System.out.println("Вы не можете быть исследователем.");
    }

    public void displaySuccessBecomingResearcher() {
        System.out.println("Теперь вы исследователь!");
    }

    public void displayAlreadyIsResearcher() {
        System.out.println("Вы уже являетесь исследователем.");
    }

    public ResearchPaper getResearchPaper() {
        System.out.println("=== Создание новой научной статьи ===");

        System.out.println("Введите название научной статьи:");
        String title = InputValidatorUtil.validateNonEmptyInput("Название не может быть пустым.");

        System.out.println("Введите тезис статьи:");
        String thesis = InputValidatorUtil.validateNonEmptyInput("Тезис не может быть пустым.");

        System.out.println("Хотите сгенерировать DOI автоматически или ввести вручную?");
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
                    System.out.println("DOI не уникален. Введите другой DOI:");
                }
            }
        }

        Journal journal = selectJournal();

        System.out.println("Введите количество цитирований:");
        Integer citations = InputValidatorUtil.validateIntegerInput("Количество цитирований должно быть положительным числом.", 0, Integer.MAX_VALUE);

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> authors = selectAuthors(allResearchers);
        authors.add(Menu.getInstance().getLoggedUser());

        System.out.println("Введите дату публикации (yyyy-MM-dd):");
        String publicationDate = InputValidatorUtil.validateDateInput("Неверный формат даты. Попробуйте снова.");

        ResearchPaper researchPaper = new ResearchPaper(title, thesis, journal, citations, doi, LocalDate.parse(publicationDate), authors);

        System.out.println("Научная статья успешно создана.");
        return researchPaper;
    }

    public Journal selectJournal() {
        System.out.println("Доступные журналы:");
        Set<Journal> journals = storage.getPapersByJournal().keySet();

        if (journals.isEmpty()) {
            System.out.println("Нет доступных журналов. Создайте новый журнал.");
            return createNewJournal();
        }

        int index = 1;
        List<Journal> journalList = new ArrayList<>(journals);
        for (Journal journal : journalList) {
            System.out.printf("%d. %s%n", index++, journal.getName());
        }

        System.out.println(index + ". Создать новый журнал");
        int choice = InputValidatorUtil.validateIntegerInput("Выберите журнал: ", 1, index);

        return (choice == index) ? createNewJournal() : journalList.get(choice - 1);
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
            System.out.println("Нет доступных авторов.");
            return selectedAuthors;
        }

        System.out.println("Выберите авторов (введите номера через запятую):");
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
                System.out.println("Неверный ввод. Введите числа через запятую.");
            }
        }
        return selectedAuthors;
    }

    @Override
    public void displayResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("\n=== Нет научных статей ===");
            System.out.println("У вас еще нет ни одной научной статьи.");
            return;
        }

        System.out.println("\n=== Ваши научные статьи ===");
        System.out.printf("%-5s %-35s %-25s %-12s %-35s %-15s%n",
                "No.", "Заголовок", "Журнал", "Цитирования", "DOI", "Дата публикации");
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
            System.out.println("\n=== Нет научных проектов ===");
            System.out.println("Вы не участвуете в ни одном научном проекте.");
            return;
        }

        System.out.println("\n=== Ваши научные проекты ===");
        System.out.printf("%-5s %-35s %-50s %-20s %-20s %-50s%n",
                "No.", "Заголовок", "Описание", "Начало", "Конец", "Опубликованные статьи");
        System.out.println("-".repeat(180));

        int index = 1;
        for (ResearchProject project : projects) {
            String paperTitles = project.getPublishedPapers().isEmpty()
                    ? "Нет статей"
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
        System.out.println("Выберите предпочтительный способ сортировки:");
        System.out.println("1. По дате публикации");
        System.out.println("2. По цитированиям");
        System.out.println("3. По длине заголовка");

        int choice = InputValidatorUtil.validateIntegerInput("Введите валидное число:  ", 1, 3);

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
            default -> throw new NotExistingComparatorException("Неверный выбор!");
        }
    }

    @Override
    public School getSchool() {
        System.out.println("Введите школу:");
        return EnumSelectionUtil.selectEnum(School.class);
    }

    @Override
    public void showTopCitedResearcher(User user, int totalCitations, int year) {
        if (user == null) {
            System.out.println("Нет самого цитируемого исследователя.");
            return;
        }

        System.out.println("\n=== Самый цитируемый исследователь ===");
        System.out.printf("%-20s: %s %s%n", "Имя", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %d%n", "Общее число цитирований", totalCitations);
        System.out.printf("%-20s: %d%n", "Год", year);
        System.out.println("=".repeat(50));
    }

    @Override
    public void showTopCitedResearcherOfSchool(User user, School school, int totalCitations) {
        if (user == null) {
            System.out.println("Не найден исследователь с наибольшим количеством цитирований для школы: " + school);
            return;
        }

        System.out.println("\n=== Исследователь с наибольшим количеством цитирований по школе ===");
        System.out.printf("%-20s: %s %s%n", "Имя", user.getFirstName(), user.getLastName());
        System.out.printf("%-20s: %s%n", "Школа", school);
        System.out.printf("%-20s: %d%n", "Общее количество цитирований", totalCitations);
        System.out.println("=".repeat(50));
    }

    @Override
    public int selectPublicationYear(List<Integer> years) {
        if (years.isEmpty()) {
            System.out.println("Нет доступных годов публикации.");
            return -1;
        }

        System.out.println("Доступные годы публикации:");
        for (int i = 0; i < years.size(); i++) {
            System.out.printf("%d. %d%n", i + 1, years.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput(
                "Выберите год, указав его номер: ", 1, years.size());

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
        System.out.println("=== Создание нового исследовательского проекта ===");

        System.out.println("Введите название исследовательского проекта:");
        String title = InputValidatorUtil.validateNonEmptyInput("Название не может быть пустым.");

        System.out.println("Введите описание исследовательского проекта:");
        String description = InputValidatorUtil.validateNonEmptyInput("Описание не может быть пустым.");

        Journal journal = selectJournal();

        System.out.println("Введите дату начала проекта (yyyy-MM-dd):");
        String startDateString = InputValidatorUtil.validateDateInput("Неверный формат даты. Попробуйте снова.");
        LocalDateTime startDate = LocalDateTime.parse(startDateString + "T00:00:00");

        System.out.println("Введите дату окончания проекта (yyyy-MM-dd):");
        String endDateString = InputValidatorUtil.validateDateInput("Неверный формат даты. Попробуйте снова.", LocalDate.parse(startDateString));
        LocalDateTime endDate = LocalDateTime.parse(endDateString + "T23:59:59");

        List<User> allResearchers = researcherService.getAllResearchersExceptAuthor();
        Vector<User> participants = selectAuthors(allResearchers);
        participants.add(Menu.getInstance().getLoggedUser());

        Vector<ResearchPaper> papers = selectResearchPapers(participants);

        ResearchProject project = new ResearchProject(title, description, journal, startDate, endDate, participants, papers);

        System.out.println("Исследовательский проект успешно создан.");
        return project;
    }

    public Vector<ResearchPaper> selectResearchPapers(Vector<User> participants) {
        Vector<ResearchPaper> selectedPapers = new Vector<>();

        System.out.println("Выбор исследовательских статей для проекта:");
        for (User participant : participants) {
            List<ResearchPaper> papers = researcherService.getResearchPapers(participant);
            if (papers.isEmpty()) {
                System.out.println("Нет доступных исследовательских статей для участника: " + participant.getFirstName() + " " + participant.getLastName());
                continue;
            }

            System.out.println("Исследовательские статьи участника " + participant.getFirstName() + " " + participant.getLastName() + ":");
            for (int i = 0; i < papers.size(); i++) {
                System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
            }

            System.out.println("Выберите номера статей (через запятую) или нажмите Enter, чтобы пропустить:");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    String[] indices = input.split(",");
                    for (String index : indices) {
                        int paperIndex = Integer.parseInt(index.trim()) - 1;
                        if (paperIndex >= 0 && paperIndex < papers.size()) {
                            selectedPapers.add(papers.get(paperIndex));
                        } else {
                            System.out.println("Недействительный номер статьи: " + (paperIndex + 1));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Пропуск выбора статей для этого участника.");
                }
            }
        }

        return selectedPapers;
    }

    @Override
    public ResearchPaper selectResearchPaper(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("Нет доступных исследовательских статей.");
            return null;
        }

        System.out.println("Доступные исследовательские статьи:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, papers.get(i).getTitle());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Выберите статью, указав её номер: ", 1, papers.size());
        return papers.get(choice - 1);
    }

    @Override
    public Format selectCitationFormat() {
        System.out.println("Выберите формат цитирования:");
        System.out.println("1. Обычный текст");
        System.out.println("2. BibTeX");

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, 2);

        return switch (choice) {
            case 1 -> Format.PLAIN_TEXT;
            case 2 -> Format.BIBTEX;
            default -> throw new InvalidFormatException("Неверный выбор!");
        };
    }

    @Override
    public void displayCitation(ResearchPaper paper, Format format) {
        System.out.println("\n=== Цитирование ===");

        String citation = switch (format) {
            case PLAIN_TEXT -> String.format("%s. %s. %s. DOI: %s. Опубликовано: %s.",
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
