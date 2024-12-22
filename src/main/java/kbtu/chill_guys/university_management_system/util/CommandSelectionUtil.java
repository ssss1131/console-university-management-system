package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;

import java.util.List;

import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.CHOOSE_COMMAND_MESSAGE;
import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.INVALID_OPTION_MESSAGE;

public class CommandSelectionUtil {

    private CommandSelectionUtil() {
    }

    public static <T> T selectCommand(List<T> commands) {
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, commands.get(i).toString());
        }

        Language language = Menu.getInstance().getLanguage();
        System.out.println(CHOOSE_COMMAND_MESSAGE.get(language));
        int choice = InputValidatorUtil.validateIntegerInput(
                INVALID_OPTION_MESSAGE.get(language),
                1, commands.size()
        );

        return commands.get(choice - 1);
    }
}
