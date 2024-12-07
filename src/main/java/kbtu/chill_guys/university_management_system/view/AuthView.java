package main.java.kbtu.chill_guys.university_management_system.view;

import java.util.Scanner;

public class AuthView {
    private final Scanner scanner = new Scanner(System.in);

    public String getEmail() {
        System.out.println("Enter your email: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Enter your password: ");
        return scanner.nextLine();
    }

    public void displayLoginSuccess() {
        System.out.println("Login successful! Welcome!");
    }

    public void displayLoginFailure() {
        System.out.println("Login failed. Invalid email or password.");
    }
}
