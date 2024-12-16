package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;

import java.util.List;

public class GeneralView {

    public School showSchoolSelection(List<School> schools) {
        System.out.println("Choose a school or 'ALL' to view disciplines:");
        System.out.println("1. ALL");
        int index = 2;

        for (School school : schools) {
            System.out.printf("%d. %s%n", index++, school.name());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, index - 1);

        if (choice == 1) {
            return null;
        }

        return schools.get(choice - 2);
    }

    public void displayDisciplines(List<Discipline> disciplines) {
        if (disciplines.isEmpty()) {
            System.out.println("No disciplines available.");
            return;
        }

        System.out.println("\n=== Disciplines ===");
        for (Discipline discipline : disciplines) {
            System.out.printf("Code: %s, Name: %s, Credits: %d, Semester: %s%n",
                    discipline.getCode(), discipline.getName(), discipline.getCredits(), discipline.getSemester());
        }
        System.out.println("===================\n");
    }
}
