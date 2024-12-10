package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.controller.StudentController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class StudentView {

    private static final Logger logger = Logger.getLogger(StudentView.class.getName());

    public void displayInfo(Student student) {
        System.out.println("Hello " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Your ID: " + student.getId());
        System.out.println("Study duration: " + student.getStudyDuration());
        System.out.println("School: " + student.getSchool());
        System.out.println("Organization: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());


    }

    public void displayTranscript() {


    }

    public void displayOrganizationsFeatures() {

    }

    public void displayAllTeachersForRating() {
        //TODO
    }

    public void displayAttendance() {
        //TODO
    }

    public void displayAcademicStanding() {
        //TODO
    }
    public void showDisciplineMenu(Student student, StudentController studentController) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Шаг 1: Получение года
            System.out.print("Enter year (e.g., 2023-2024): ");
            String year = scanner.nextLine();

            // Шаг 2: Получение периода
            System.out.print("Enter period (SPRING/FALL): ");
            Period period = Period.valueOf(scanner.nextLine().toUpperCase());

            // Шаг 3: Получение и отображение курсов
            List<String> courses = studentController.getDiscipline(student, year, period);
            System.out.println("Courses for " + year + " " + period + ":");
            if (courses.isEmpty()) {
                System.out.println("No courses found.");
            } else {
                courses.forEach(course -> System.out.println("  - " + course));
            }
        } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
        }
    }
    
    
}
