package main.java.kbtu.chill_guys.university_management_system.util;

import java.util.List;

public class CommandSelectionUtil {

    private CommandSelectionUtil() {
    }

    public static <T> T selectCommand(List<T> commands) {
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, commands.get(i).toString());
        }

        System.out.println("Choose a number: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Invalid number. Please enter a valid option.",
                1, commands.size()
        );

        return commands.get(choice - 1);
    }
}
