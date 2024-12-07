package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);

    public Map<String, Object> getUserInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Enter user role (e.g., admin, student): ");
        data.put("role", UserRole.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter email: ");
        data.put("email", scanner.nextLine());

        System.out.println("Enter password: ");
        data.put("password", scanner.nextLine());

        System.out.println("Enter first name: ");
        data.put("firstName", scanner.nextLine());

        System.out.println("Enter last name: ");
        data.put("lastName", scanner.nextLine());

        return data;
    }

    public void displayUserCreated(User user) {
        System.out.println("User created successfully!");
        System.out.println(user);
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
