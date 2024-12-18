package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

import java.util.Scanner;

public class AuthViewEn implements AuthView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getEmail() {
        System.out.println("Enter your email: ");
        return scanner.nextLine();
    }

    @Override
    public String getPassword() {
        System.out.println("Enter your password: ");
        return scanner.nextLine();
    }

    @Override
    public void displayLoginSuccess(String userFirstName, String userLastName) {
        System.out.println("Login successful! Welcome " + userFirstName + " " + userLastName + "!");
    }

    @Override
    public void displayLoginFailure() {
        System.out.println("Login failed. Invalid email or password.");
    }

    @Override
    public void displayLogoutMessage(boolean isLoggedIn) {
        if (!isLoggedIn) {
            System.out.println("No user is currently logged in.");
        } else {
            System.out.println("Successfully logged out.");
        }
    }
}

