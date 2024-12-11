package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private static final Menu INSTANCE = new Menu();
    private static final Logger LOGGER = Logger.getLogger(Menu.class.getName());
    private Language language;
    private User loggedUser;
    private Map<String, Command> commands = new HashMap<>();
    private final Map<String, UserRole[]> commandRoles = new HashMap<>();

    private Menu(){

    }

    public static Menu getInstance(){
        return INSTANCE;
    }

    public Language getLanguage() {
        return this.language;
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

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Application starting...");
        while (true) {
            displayAvailableCommands();
            System.out.println("Enter command: ");
            String commandName = scanner.nextLine().toLowerCase();
            Command command = commands.get(commandName);

            if (command != null && isAuthorized(commandName)) {
                command.execute();
                LOGGER.info("Command " + commandName + " executed successfully.");
            } else {
                System.out.println("Unknown command or insufficient permissions.");
            }
        }
    }

    public void registerCommand(String commandName, Command command, UserRole... roles) {
        String normalizedCommandName = commandName.toLowerCase();
        commands.put(normalizedCommandName, command);
        commandRoles.put(normalizedCommandName, roles);
    }

    public void displayAvailableCommands() {
        if (loggedUser == null) {
            System.out.println("Available commands:");
            for (String commandName : commands.keySet()) {
                if (commandName.equals("login")) {
                    System.out.println("- " + commandName);
                }
            }
        } else {
            System.out.println("Current user: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
            System.out.println("Available commands:");
            for (String commandName : commands.keySet()) {
                if (isAuthorized(commandName)) {
                    System.out.println("- " + commandName);
                }
            }
        }
    }

    private boolean isAuthorized(String commandName) {
        if (loggedUser == null) {
            return commandName.equals("login");
        }

        if (commandName.equals("logout")) {
            return true;
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
