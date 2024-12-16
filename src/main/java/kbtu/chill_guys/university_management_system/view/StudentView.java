package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;

import java.util.*;
import java.util.logging.Logger;

public class StudentView {

    private static final Logger logger = Logger.getLogger(StudentView.class.getName());

    private final Scanner scanner = new Scanner(System.in);

    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("No available disciplines for registration.");
            return;
        }

        System.out.println("Available disciplines for registration:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Code: %s, Credits: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Enter the numbers of disciplines to register (comma-separated):");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                List<Discipline> selectedDisciplines = Arrays.stream(parts)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(i -> i >= 1 && i <= availableDisciplines.size())
                        .mapToObj(i -> availableDisciplines.get(i - 1))
                        .toList();

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("No disciplines selected. Please try again.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers separated by commas.");
            }
        }
    }

    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("You have successfully registered for the following disciplines:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Code: %s, Credits: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    public void displayInfo(Student student) {
        System.out.println("Hello " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Your ID: " + student.getId());
        System.out.println("Study duration: " + student.getStudyDuration());
        System.out.println("School: " + student.getSchool());
        System.out.println("Organization: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());

    }

    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Registered Disciplines ===");
        System.out.println("Semester: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("You have not registered for any disciplines this semester.");
            return;
        }

        System.out.printf("%-10s %-30s %-10s %-15s%n", "Code", "Name", "Credits", "Course Type");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
        System.out.println("===============================================================\n");
    }


    public void showMessage(String message){
        System.out.println(message);

    }


    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Would you like to view disciplines for a specific semester or all semesters?");
        System.out.println("1. Specific Semester");
        System.out.println("2. All Semesters");

        int choice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("No semesters available.");
            return null;
        }

        System.out.println("Select a semester:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }


    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("No disciplines registered for the selected semester.");
            return;
        }

        System.out.println("\n=== Registered Disciplines for the Selected Semester ===");
        showDisciplineList(disciplines);
        System.out.println("=========================================================\n");
    }

    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("No disciplines registered across semesters.");
            return;
        }

        System.out.println("\n=== All Registered Disciplines ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Semester: " + semester);
            showDisciplineList(disciplines);
        }
        System.out.println("==================================\n");
    }


    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Code", "Name", "Credits", "Course Type");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
    }


}
