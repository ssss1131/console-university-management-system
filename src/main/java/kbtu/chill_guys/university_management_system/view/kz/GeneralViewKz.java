package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.GeneralView;

import java.util.List;

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
}