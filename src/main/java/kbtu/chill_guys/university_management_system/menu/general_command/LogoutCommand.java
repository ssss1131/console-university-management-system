package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;

public class LogoutCommand implements Command {
    private final Menu menu;

    public LogoutCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        if (menu.getLoggedUser() == null) {
            System.out.println("No user is currently logged in.");
        } else {
            menu.setLoggedUser(null);
            System.out.println("Successfully logged out.");
        }
    }
}
