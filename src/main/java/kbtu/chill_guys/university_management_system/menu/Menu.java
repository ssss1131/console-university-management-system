package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.CommandEnum;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.util.CommandSelectionUtil;

import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.researcherMethods;

public class Menu {
    private static final Menu INSTANCE = new Menu();
    private static final Logger LOGGER = Logger.getLogger(Menu.class.getName());
    private Language language = Language.EN;
    private User loggedUser;
    private final Map<CommandEnum, Command> commands = new HashMap<>();
    private final Map<CommandEnum, UserRole[]> commandRoles = new HashMap<>();

    private Menu() {
    }

    public static Menu getInstance() {
        return INSTANCE;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void registerCommand(CommandEnum command, Command implementation, UserRole... roles) {
        commands.put(command, implementation);
        commandRoles.put(command, roles);
    }

    public void run() {
        LOGGER.info("Application starting...");
        while (true) {
            System.out.println("\nAvailable commands:");
            List<CommandEnum> availableCommands = displayAvailableCommands();

            String selectedCommand = CommandSelectionUtil.selectCommand(
                    availableCommands.stream()
                            .map(cmd -> cmd.getTranslation(language))
                            .toList()
            );

            Optional<CommandEnum> selectedEnum = availableCommands.stream()
                    .filter(cmd -> cmd.getTranslation(language).equals(selectedCommand))
                    .findFirst();

            if (selectedEnum.isPresent() && isAuthorized(selectedEnum.get())) {
                commands.get(selectedEnum.get()).execute();
                LOGGER.info("Command " + selectedEnum.get().getTranslation(language) + " executed successfully.");
            } else {
                System.out.println("Unknown command or insufficient permissions.");
            }
        }
    }

    private List<CommandEnum> displayAvailableCommands() {
        List<CommandEnum> availableCommands = new ArrayList<>();

        if (loggedUser == null) {
            availableCommands.add(CommandEnum.LOGIN);
        } else {
            boolean isResearcher = ResearcherService.getInstance().isResearcher(loggedUser);


            commands.keySet().stream()
                    .filter(cmd -> cmd != CommandEnum.LOGIN)
                    .filter(this::isAuthorized)
                    .filter(cmd -> isResearcher || !researcherMethods.contains(cmd))
                    .filter(cmd -> !(cmd.equals(CommandEnum.ADD_RESEARCHER) && isResearcher))
                    .forEach(availableCommands::add);
        }

        if (!availableCommands.contains(CommandEnum.SELECT_LANGUAGE)) {
            availableCommands.add(CommandEnum.SELECT_LANGUAGE);
        }

        return availableCommands;
    }

    private boolean isAuthorized(CommandEnum command) {
        if (command == CommandEnum.LOGIN || command == CommandEnum.LOGOUT || command == CommandEnum.SELECT_LANGUAGE) {
            return true;
        }

        if (loggedUser == null) {
            return false;
        }

        UserRole[] roles = commandRoles.get(command);
        if (roles == null) return true;

        for (UserRole role : roles) {
            if (role == loggedUser.getRole()) {
                return true;
            }
        }
        return false;
    }
}
