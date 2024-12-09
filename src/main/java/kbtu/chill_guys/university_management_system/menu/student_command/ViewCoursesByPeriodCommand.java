package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.controller.StudentController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.Scanner;

public class ViewCoursesByPeriodCommand implements Command {
    private final StudentController studentController = new StudentController();
    private final StudentView studentView = new StudentView();
    private final Student student;

    public ViewCoursesByPeriodCommand(Student student) {
        this.student = student;
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);

            // Шаг 1: Показать доступные годы через вьюшку
            var availableYears = studentController.getAvailableYears(student);
            studentView.printAvailableYears(availableYears);

            // Шаг 2: Выбор года
            System.out.print("Select a year: ");
            String selectedYear = scanner.nextLine();
            if (!availableYears.contains(selectedYear)) {
                System.out.println("Invalid year selected.");
                return;
            }

            // Шаг 3: Показать доступные периоды через вьюшку
            var availablePeriods = studentController.getAvailablePeriods(student, selectedYear);
            studentView.printAvailablePeriods(availablePeriods);

            // Шаг 4: Выбор периода
            System.out.print("Select a period (SPRING/FALL): ");
            String selectedPeriod = scanner.nextLine().toUpperCase();

            // Шаг 5: Показать курсы через вьюшку
            studentController.viewCoursesByYearAndPeriod(student, selectedYear, selectedPeriod);

        } catch (Exception e) {
            System.out.println("An error occurred while displaying courses: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

