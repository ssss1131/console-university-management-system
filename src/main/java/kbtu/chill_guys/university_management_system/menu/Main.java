package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.repository.JournalRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.LogRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.service.AuthService;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.PublishPostCommand;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        LoggerUtil.configureLogging();

        UserRepository userRepository = new UserRepository(Paths.get("account.ser"));
        LogRepository logRepository = new LogRepository();
        JournalRepository journalRepository = new JournalRepository(Paths.get("journal.ser"));

        AdminService adminService = new AdminService(userRepository, logRepository);
        AdminController adminController = new AdminController(adminService);
        AdminView adminView = new AdminView();

        AuthService authService = new AuthService(userRepository);
        AuthController authController = new AuthController(authService);
        AuthView authView = new AuthView();

        JournalService journalService = new JournalService(journalRepository);
        JournalController journalController = new JournalController(journalService);
        JournalView journalView = new JournalView();

        Menu menu = new Menu();

        menu.registerCommand("getLogs", new GetLogsCommand(adminController, adminView));
        menu.registerCommand("createUser", new CreateUserCommand(adminController, adminView));
        menu.registerCommand("updateUser", new UpdateUserCommand(adminController, adminView));
        menu.registerCommand("deleteUser", new DeleteUserCommand(adminController, adminView));

        menu.registerCommand("login", new LoginCommand(authController, authView, menu));
        menu.registerCommand("logout", new LogoutCommand(menu));

        menu.registerCommand("createJournal", new CreateJournalCommand(journalController, journalView));
        menu.registerCommand("deleteJournal", new DeleteJournalCommand(journalController, journalView));
        menu.registerCommand("publishPost", new PublishPostCommand(journalController, journalView));


        menu.run();
    }
}
