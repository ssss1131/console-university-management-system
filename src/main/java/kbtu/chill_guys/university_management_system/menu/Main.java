package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;


import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerUtil.configureLogging();

        Menu menu = Menu.getInstance();

        menu.registerCommand("createUser", new CreateUserCommand());
        menu.registerCommand("updateUser", new UpdateUserCommand());
        menu.registerCommand("deleteUser", new DeleteUserCommand());
        menu.registerCommand("login", new LoginCommand());
        menu.registerCommand("logout", new LogoutCommand());

        menu.run();
    }
}
