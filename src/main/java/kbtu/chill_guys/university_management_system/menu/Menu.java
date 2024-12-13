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
            System.out.println();
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
    /**
    * можно вместо передачи ролей которые могут использовать команду просто регать команду для каждой модели
    * допустим при login делать что то на подобий user.registerCommands() абстрактный метод который реализуют все наследники
     * Но это нарушить mvc так чисто первая на ум пришедшая идея. Либо в каждой папке command как раз там по моделям разделено
     * создать новую команду для регистрации комманд данного юзера и потом один раз вызовим после login комманды в классе LoginCommand
     * МИНУСЫ МОЕГО ПОДХОДА:
     * первый подход с абстрактным методом выглядит элегантно но нарушает мвс
     * второй подход впринципе неплох пока прям явных минусов не вижу
     * МИНУСЫ ЭТОГО ПОДХОДА:
     * если мы удалим модель допустим или UserRole то придется менять везде код
     * а если удалить с вторым подходом то там чисто удалим команду и уберем в логин строчку
     * а с первым подходом вообще ниче делать не надо.
     **/
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
    //TODO поменять название или разделить на два метода чет название супер странное
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
