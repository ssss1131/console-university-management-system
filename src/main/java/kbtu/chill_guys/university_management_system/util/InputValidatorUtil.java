package main.java.kbtu.chill_guys.university_management_system.util;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.EMAIL_PATTERN;

public final class InputValidatorUtil {

    private static final Scanner scanner = new Scanner(System.in);

    private InputValidatorUtil() {
    }

    public static String validateNonEmptyInput(String errorMessage) {
        while (true) {
            String input = scanner.nextLine();
            if (!input.isBlank()) {
                return input.trim();
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static String validateEmailInput(String errorMessage) {
        while (true) {
            String email = scanner.nextLine().trim();
            if (EMAIL_PATTERN.matcher(email).matches()) {
                return email;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static int validateIntegerInput(String errorMessage, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String validateDateInput(String errorMessage) {
        while (true) {
            try {
                System.out.println("Enter date (yyyy-MM-dd):");
                String input = scanner.nextLine().trim();
                LocalDate.parse(input);
                return input;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static UUID validateUUIDInput(String errorMessage) {
        while (true) {
            try {
                return UUID.fromString(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }
}

