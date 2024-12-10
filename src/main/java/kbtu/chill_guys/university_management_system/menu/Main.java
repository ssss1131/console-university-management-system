package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.PublishPostCommand;

public class Main {

    public static void main(String[] args) {
        LoggerUtil.configureLogging();
        Menu menu = Menu.getInstance();
        menu.registerCommand("getLogs", new GetLogsCommand());
        menu.registerCommand("createUser", new CreateUserCommand());
        menu.registerCommand("updateUser", new UpdateUserCommand());
        menu.registerCommand("deleteUser", new DeleteUserCommand());

        menu.registerCommand("login", new LoginCommand());
        menu.registerCommand("logout", new LogoutCommand());

        menu.registerCommand("createJournal", new CreateJournalCommand());
        menu.registerCommand("deleteJournal", new DeleteJournalCommand());
        menu.registerCommand("publishPost", new PublishPostCommand());


        menu.run();
    }
}

