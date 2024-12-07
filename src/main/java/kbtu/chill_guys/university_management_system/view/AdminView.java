package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);

    public Map<String, Object> getUserInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Enter user role (e.g., admin, student, teacher, manager, dean): ");
        UserRole role = UserRole.valueOf(scanner.nextLine().toUpperCase());
        data.put("role", role);

        System.out.println("Enter email: ");
        data.put("email", scanner.nextLine());

        System.out.println("Enter password: ");
        data.put("password", scanner.nextLine());

        System.out.println("Enter first name: ");
        data.put("firstName", scanner.nextLine());

        System.out.println("Enter last name: ");
        data.put("lastName", scanner.nextLine());

        switch (role) {
            case STUDENT: handleStudentInput(data);
            case TEACHER: handleTeacherInput(data);
            case MANAGER: handleManagerInput(data);
            case DEAN: handleDeanInput(data);
        }

        return data;
    }

    public void displayUserCreated(User user) {
        System.out.println("User created successfully!");
        System.out.println(user);
    }

    private void handleStudentInput(Map<String, Object> data) {
        System.out.println("Enter school: ");
        data.put("school", School.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter enrollment date (yyyy-mm-dd): ");
        data.put("enrollmentDate", scanner.nextLine());

        System.out.println("Enter GPA: ");
        data.put("gpa", Double.parseDouble(scanner.nextLine()));

        System.out.println("Enter credits: ");
        data.put("credits", Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter study duration (years): ");
        data.put("studyDuration", Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter organization: ");
        data.put("organization", getOrganizationInput());
    }

    private void handleTeacherInput(Map<String, Object> data) {
        System.out.println("Enter rating: ");
        data.put("rating", Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter school: ");
        data.put("school", School.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter teaching degree: ");
        data.put("teachingDegree", scanner.nextLine());
    }

    private void handleManagerInput(Map<String, Object> data) {
        System.out.println("Enter salary: ");
        data.put("salary", Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter manager type: ");
        data.put("managerType", ManagerType.valueOf(scanner.nextLine().toUpperCase()));
    }

    private void handleDeanInput(Map<String, Object> data) {
        System.out.println("Enter salary: ");
        data.put("salary", Integer.parseInt(scanner.nextLine()));
    }

    private Organization getOrganizationInput() {
        System.out.println("Enter organization name: ");
        String name = scanner.nextLine();

        System.out.println("Enter organization description: ");
        String description = scanner.nextLine();

        Map<Student, OrganizationRole> members = new HashMap<>();

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);
        organization.setMembers(members);

        return organization;
    }


    public void displayLogs() {
        //TODO
    }

    public void updateUser() {
        //TODO
    }
    
    public void deleteUser() {
        //TODO
    }
}
