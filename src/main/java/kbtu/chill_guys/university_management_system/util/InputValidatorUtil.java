package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.EMAIL_PATTERN;
import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.*;

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
                System.out.println(INVALID_NUMBER_MESSAGE.get(Menu.getInstance().getLanguage()));
            }
        }
    }

    public static String validateDateInput(String errorMessage) {
        while (true) {
            try {
                System.out.println(ENTER_DATE_MESSAGE.get(Menu.getInstance().getLanguage()));
                String input = scanner.nextLine().trim();
                LocalDate.parse(input);
                return input;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static String validateDateInput(String errorMessage, LocalDate prevDate) {
        while (true) {
            try {
                System.out.println(ENTER_DATE_MESSAGE.get(Menu.getInstance().getLanguage()));
                String input = new Scanner(System.in).nextLine().trim();
                LocalDate enteredDate = LocalDate.parse(input);

                if (prevDate != null && !enteredDate.isAfter(prevDate)) {
                    System.out.println(DATE_AFTER_MESSAGE.get(Menu.getInstance().getLanguage()) + prevDate);
                    continue;
                }

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

