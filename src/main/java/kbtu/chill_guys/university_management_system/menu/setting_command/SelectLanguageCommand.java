package main.java.kbtu.chill_guys.university_management_system.menu.setting_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;

import java.util.Scanner;

public class SelectLanguageCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Select a language:");
        System.out.println("1. English");
        System.out.println("2. Russian");
        System.out.println("3. Kazakh");

        int choice = scanner.nextInt();
        Language selectedLanguage;

        switch (choice) {
            case 1 -> selectedLanguage = Language.EN;
            case 2 -> selectedLanguage = Language.RU;
            case 3 -> selectedLanguage = Language.KZ;
            default -> {
                System.out.println("Invalid choice. Defaulting to English.");
                selectedLanguage = Language.EN;
            }
        }

        Menu.getInstance().setLanguage(selectedLanguage);
        System.out.println("Language set to " + selectedLanguage);
    }
}
