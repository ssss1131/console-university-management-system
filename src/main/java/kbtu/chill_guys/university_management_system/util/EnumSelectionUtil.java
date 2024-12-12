package main.java.kbtu.chill_guys.university_management_system.util;

import java.util.Scanner;

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
}

