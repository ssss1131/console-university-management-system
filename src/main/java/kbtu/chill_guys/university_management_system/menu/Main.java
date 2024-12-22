package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.util.CommandRegistrar;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;


public class Main {
    public static void main(String[] args) {
        LoggerUtil.configureLogging();
        Menu menu = Menu.getInstance();

        CommandRegistrar.registerAllCommands(menu);

        menu.run();
    }
}
