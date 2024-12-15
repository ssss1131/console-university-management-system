package main.java.kbtu.chill_guys.university_management_system.util;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EnumSelectionUtil {

    private EnumSelectionUtil(){
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static <E extends Enum<E>> E selectEnum(Class<E> enumClass) {
        E[] enumValues = enumClass.getEnumConstants();

        for (int i = 0; i < enumValues.length; i++) {
            System.out.printf("%d. %s%n", i + 1, enumValues[i].name());
        }

        while (true) {
            try {
                System.out.println("Choose number:");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= enumValues.length) {
                    return enumValues[choice - 1];
                } else {
                    System.out.println("Incorrect number. Please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter correct number");
            }
        }
    }

    public static <E extends Enum<E>> Set<E> selectMultipleEnums(Class<E> enumClass) {
        E[] enumValues = enumClass.getEnumConstants();
        Set<E> selectedEnums = new HashSet<>();

        for (int i = 0; i < enumValues.length; i++) {
            System.out.printf("%d. %s%n", i + 1, enumValues[i].name());
        }

        System.out.println("Choose numbers separated by commas (e.g., 1,2,3) or leave empty for none:");

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("No selections made.");
                    return selectedEnums;
                }

                String[] choices = input.split(",");
                for (String choice : choices) {
                    int index = Integer.parseInt(choice.trim()) - 1;
                    if (index >= 0 && index < enumValues.length) {
                        selectedEnums.add(enumValues[index]);
                    } else {
                        System.out.printf("Invalid number: %s. Skipping.%n", choice);
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers separated by commas.");
            }
        }

        return selectedEnums;
    }

}

