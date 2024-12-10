package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.controller.StudentController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.List;
import java.util.Scanner;

public class ViewCoursesCommand implements Command {
    private final StudentController studentController;
    private final Student student;

    public ViewCoursesCommand(StudentController studentController, Student student) {
        this.studentController = studentController;
        this.student = student;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Шаг 1: Ввод года
            System.out.print("Enter year (e.g., 2023-2024): ");
            String year = scanner.nextLine();

            // Шаг 2: Ввод периода
            System.out.print("Enter period (SPRING/FALL): ");
            String periodInput = scanner.nextLine().toUpperCase();
            Period period = Period.valueOf(periodInput); // Преобразуем в enum

            // Шаг 3: Получение курсов через контроллер
            List<String> courses = studentController.getCourses(student, year, period);

            // Шаг 4: Отображение курсов
            System.out.println("Courses for " + year + " " + period + ":");
            if (courses.isEmpty()) {
                System.out.println("  No courses found.");
            } else {
                courses.forEach(course -> System.out.println("  - " + course));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
