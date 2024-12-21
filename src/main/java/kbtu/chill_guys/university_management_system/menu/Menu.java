package main.java.kbtu.chill_guys.university_management_system.menu;

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
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, UserRole[]> commandRoles = new HashMap<>();

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

    public void registerCommand(String commandName, Command command, UserRole... roles) {
        commands.put(commandName, command);
        commandRoles.put(commandName, roles);
    }

    public void run() {
        LOGGER.info("Application starting...");
        while (true) {
            System.out.println("\nAvailable commands:");
            List<String> availableCommands = displayAvailableCommands();

            String selectedCommandName = CommandSelectionUtil.selectCommand(availableCommands);

            Command command = commands.get(selectedCommandName);
            if (command != null && isAuthorized(selectedCommandName)) {
                command.execute();
                LOGGER.info("Command " + selectedCommandName + " executed successfully.");
            } else {
                System.out.println("Unknown command or insufficient permissions.");
            }
        }
    }

    private List<String> displayAvailableCommands() {
        System.out.println("Current language: " + language);
        List<String> availableCommands = new ArrayList<>();

        if (loggedUser == null) {
            commands.keySet().stream()
                    .filter(cmd -> cmd.equalsIgnoreCase("login"))
                    .forEach(availableCommands::add);
        } else {
            System.out.println("Current user: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());

            boolean isResearcher = ResearcherService.getInstance().isResearcher(loggedUser);

            commands.keySet().stream()
                    .filter(cmd -> !cmd.equalsIgnoreCase("login"))
                    .filter(this::isAuthorized)
                    .filter(cmd -> isResearcher || !researcherMethods.contains(cmd))
                    .filter(cmd -> !(cmd.equalsIgnoreCase("i want to be researcher!!") && isResearcher))
                    .forEach(availableCommands::add);
        }
        return availableCommands;
    }

    private boolean isAuthorized(String commandName) {
        if (commandName.equalsIgnoreCase("login") || commandName.equalsIgnoreCase("logout")) {
            return true;
        }

        if (loggedUser == null) {
            return false;
        }

        UserRole[] roles = commandRoles.get(commandName);
        if (roles == null) return true;

        for (UserRole role : roles) {
            if (role == loggedUser.getRole()) {
                return true;
            }
        }
        return false;
    }
}
