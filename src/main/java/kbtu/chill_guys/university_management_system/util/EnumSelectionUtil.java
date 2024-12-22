package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.*;

public class EnumSelectionUtil {

    private EnumSelectionUtil(){
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static <E extends Enum<E>> E selectEnum(Class<E> enumClass) {
        Language language = Menu.getInstance().getLanguage();

        E[] enumValues = enumClass.getEnumConstants();

        for (int i = 0; i < enumValues.length; i++) {
            System.out.printf("%d. %s%n", i + 1, enumValues[i].name());
        }

        while (true) {
            try {
                System.out.println(ENTER_OPTION_MESSAGE.get(language));
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= enumValues.length) {
                    return enumValues[choice - 1];
                } else {
                    System.out.println(INVALID_OPTION_MESSAGE.get(language));
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_NUMBER_MESSAGE.get(language));
            }
        }
    }

    public static <E extends Enum<E>> Set<E> selectMultipleEnums(Class<E> enumClass) {
        Language language = Menu.getInstance().getLanguage();
        E[] enumValues = enumClass.getEnumConstants();
        Set<E> selectedEnums = new HashSet<>();

        for (int i = 0; i < enumValues.length; i++) {
            System.out.printf("%d. %s%n", i + 1, enumValues[i].name());
        }

        System.out.println(ENTER_MULTIPLE_OPTIONS_MESSAGE.get(language));

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(NO_SELECTION_MADE_MESSAGE.get(language));
                    return selectedEnums;
                }

                String[] choices = input.split(",");
                for (String choice : choices) {
                    int index = Integer.parseInt(choice.trim()) - 1;
                    if (index >= 0 && index < enumValues.length) {
                        selectedEnums.add(enumValues[index]);
                    } else {
                        System.out.printf(INVALID_NUMBER_SKIP_MESSAGE.get(language), choice);
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE.get(language));
            }
        }

        return selectedEnums;
    }

}

