package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Language language;
    private User loggedUser;
    private Map<String, Command> commands = new HashMap<>();;

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
        while (true) {
            System.out.println("Enter command: ");
            String commandName = scanner.nextLine();
            Command command = commands.get(commandName);

            if (command != null) {
                command.execute();
            } else {
                System.out.println("Unknown command.");
            }
        }
    }

    public void save() {
        //TODO
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }
}
