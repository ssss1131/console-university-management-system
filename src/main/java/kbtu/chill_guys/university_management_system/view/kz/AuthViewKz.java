package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

import java.util.Scanner;

public class AuthViewKz implements AuthView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getEmail() {
        System.out.println("Электрондық поштаңызды енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public String getPassword() {
        System.out.println("Құпия сөзіңізді енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public void displayLoginSuccess(String userFirstName, String userLastName) {
        System.out.println("Кіру сәтті өтті! Қош келдіңіз, " + userFirstName + " " + userLastName + "!");
    }

    @Override
    public void displayLoginFailure() {
        System.out.println("Кіру сәтсіз аяқталды. Электрондық пошта немесе құпия сөз қате.");
    }

    @Override
    public void displayLogoutMessage(boolean isLoggedIn) {
        if (!isLoggedIn) {
            System.out.println("Ешкім жүйеге кірмеген.");
        } else {
            System.out.println("Сіз жүйеден сәтті шықтыңыз.");
        }
    }
}
