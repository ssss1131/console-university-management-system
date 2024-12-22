package main.java.kbtu.chill_guys.university_management_system.view.kz;

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

public class GeneralViewKz implements GeneralView {
    @Override
    public School showSchoolSelection(List<School> schools) {
        System.out.println("Мектепті таңдаңыз немесе пәндерді көру үшін 'БАРЛЫҚТЫ' таңдаңыз:");
        System.out.println("1. БАРЛЫҚ");
        int index = 2;

        for (School school : schools) {
            System.out.printf("%d. %s%n", index++, school.name());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, index - 1);

        if (choice == 1) {
            return null;
        }

        return schools.get(choice - 2);
    }

    @Override
    public void displayDisciplines(List<Discipline> disciplines) {
        if (disciplines.isEmpty()) {
            System.out.println("Қол жетімді пәндер жоқ.");
            return;
        }

        System.out.println("\n=== Пәндер ===");
        for (Discipline discipline : disciplines) {
            System.out.printf("Код: %s, Аты: %s, Кредиттер: %d, Семестр: %s%n",
                    discipline.getCode(), discipline.getName(), discipline.getCredits(), discipline.getSemester());
        }
        System.out.println("===================\n");
    }

    @Override
    public Journal selectJournal(Set<Journal> journals) {
        if (journals.isEmpty()) {
            System.out.println("Қолжетімді журналдар жоқ.");
            return null;
        }

        System.out.println("\n=== Қолжетімді журналдар ===");
        List<Journal> journalList = new ArrayList<>(journals);

        for (int i = 0; i < journalList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journalList.get(i).getName());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Журналды нөмірі бойынша таңдаңыз немесе бас тарту үшін 0 басыңыз: ", 0, journalList.size());

        if (choice == 0) {
            return null;
        }

        return journalList.get(choice - 1);
    }

    @Override
    public void showPosts(List<ResearchPaper> papers, List<ResearchProject> projects) {
        System.out.println("\n=== Журналдағы жазбалар ===");

        if (!papers.isEmpty()) {
            System.out.println("\n--- Ғылыми мақалалар ---");
            System.out.printf("%-5s %-35s %-50s %-15s %-10s%n", "№", "Тақырыбы", "Авторлары", "DOI", "Дәйексөздер");
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
            System.out.println("\nҒылыми мақалалар жоқ.");
        }

        if (!projects.isEmpty()) {
            System.out.println("\n--- Ғылыми жобалар ---");
            System.out.printf("%-5s %-35s %-50s %-20s %-20s%n", "№", "Тақырыбы", "Сипаттамасы", "Басталу күні", "Аяқталу күні");
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
            System.out.println("\nҒылыми жобалар жоқ.");
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