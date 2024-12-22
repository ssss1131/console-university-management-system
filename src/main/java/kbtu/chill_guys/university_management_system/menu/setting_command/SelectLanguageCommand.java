package main.java.kbtu.chill_guys.university_management_system.menu.setting_command;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.util.Constant;

import java.util.Scanner;

import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.*;

public class SelectLanguageCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        System.out.println(SELECT_LANGUAGE_MESSAGE.get(currentLanguage));
        System.out.println("1. " + LANGUAGE_EN.get(currentLanguage));
        System.out.println("2. " + LANGUAGE_RU.get(currentLanguage));
        System.out.println("3. " + LANGUAGE_KZ.get(currentLanguage));

        int choice = scanner.nextInt();
        Language selectedLanguage;

        switch (choice) {
            case 1 -> selectedLanguage = Language.EN;
            case 2 -> selectedLanguage = Language.RU;
            case 3 -> selectedLanguage = Language.KZ;
            default -> {
                System.out.println(INVALID_LANGUAGE_CHOICE.get(currentLanguage));
                selectedLanguage = Language.EN;
            }
        }

        Menu.getInstance().setLanguage(selectedLanguage);
        System.out.println(LANGUAGE_SET_MESSAGE.get(selectedLanguage));
    }
}
