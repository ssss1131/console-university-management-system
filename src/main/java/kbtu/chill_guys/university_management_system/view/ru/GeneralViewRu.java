package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.GeneralView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralViewRu implements GeneralView {

    @Override
    public School showSchoolSelection(List<School> schools) {
        System.out.println("Выберите школу или 'ВСЕ', чтобы просмотреть дисциплины:");
        System.out.println("1. ВСЕ");
        int index = 2;

        for (School school : schools) {
            System.out.printf("%d. %s%n", index++, school.name());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, index - 1);

        if (choice == 1) {
            return null;
        }

        return schools.get(choice - 2);
    }

    @Override
    public void displayDisciplines(List<Discipline> disciplines) {
        if (disciplines.isEmpty()) {
            System.out.println("Нет доступных дисциплин.");
            return;
        }

        System.out.println("\n=== Дисциплины ===");
        for (Discipline discipline : disciplines) {
            System.out.printf("Код: %s, Название: %s, Кредиты: %d, Семестр: %s%n",
                    discipline.getCode(), discipline.getName(), discipline.getCredits(), discipline.getSemester());
        }
        System.out.println("===================\n");
    }

    @Override
    public Journal selectJournal(Set<Journal> journals) {
        if (journals.isEmpty()) {
            System.out.println("Нет доступных журналов.");
            return null;
        }

        System.out.println("\n=== Доступные журналы ===");
        List<Journal> journalList = new ArrayList<>(journals);

        for (int i = 0; i < journalList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journalList.get(i).getName());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Выберите журнал по номеру или нажмите 0 для отмены: ", 0, journalList.size());

        if (choice == 0) {
            return null;
        }

        return journalList.get(choice - 1);
    }

    @Override
    public void showPosts(List<ResearchPaper> papers, List<ResearchProject> projects) {
        System.out.println("\n=== Записи в журнале ===");

        if (!papers.isEmpty()) {
            System.out.println("\n--- Научные статьи ---");
            System.out.printf("%-5s %-35s %-50s %-15s %-10s%n", "№", "Название", "Авторы", "DOI", "Цитаты");
            System.out.println("-".repeat(140));

            int index = 1;
            for (ResearchPaper paper : papers) {
                String authors = paper.getAuthors().stream()
                        .map(User::getFirstName)
                        .collect(Collectors.joining(", "));
                System.out.printf("%-5d %-35s %-50s %-15s %-10d%n",
                        index++, paper.getTitle(), authors, paper.getDoi(), paper.getCitations());
            }
        } else {
            System.out.println("\nНаучных статей нет.");
        }

        if (!projects.isEmpty()) {
            System.out.println("\n--- Научные проекты ---");
            System.out.printf("%-5s %-35s %-50s %-20s %-20s%n", "№", "Название", "Описание", "Дата начала", "Дата окончания");
            System.out.println("-".repeat(140));

            int index = 1;
            for (ResearchProject project : projects) {
                System.out.printf("%-5d %-35s %-50s %-20s %-20s%n",
                        index++, project.getTitle(),
                        truncateText(project.getDescription(), 50),
                        project.getStartDate().toLocalDate(),
                        project.getEndDate().toLocalDate());
            }
        } else {
            System.out.println("\nНаучных проектов нет.");
        }

        System.out.println("\n" + "=".repeat(140));
    }

    private String truncateText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}