package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

import java.util.Scanner;

public class AuthViewRu implements AuthView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getEmail() {
        System.out.println("Введите ваш email: ");
        return scanner.nextLine();
    }

    @Override
    public String getPassword() {
        System.out.println("Введите ваш пароль: ");
        return scanner.nextLine();
    }

    @Override
    public void displayLoginSuccess(String userFirstName, String userLastName) {
        System.out.println("Вход выполнен успешно! Добро пожаловать, " + userFirstName + " " + userLastName + "!");
    }

    @Override
    public void displayLoginFailure() {
        System.out.println("Ошибка входа. Неверный email или пароль.");
    }

    @Override
    public void displayLogoutMessage(boolean isLoggedIn) {
        if (!isLoggedIn) {
            System.out.println("Никто не вошел в систему.");
        } else {
            System.out.println("Вы успешно вышли из системы.");
        }
    }
}
