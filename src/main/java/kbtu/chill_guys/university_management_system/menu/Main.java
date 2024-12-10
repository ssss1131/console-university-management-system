package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.controller.ManagerController;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.AddNewsCommand;
import main.java.kbtu.chill_guys.university_management_system.repository.LogRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.PostRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.service.AuthService;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        LoggerUtil.configureLogging();

        PostRepository postRepository = new PostRepository(Paths.get("media_db", "post.ser"));
        NewsManagementService newsService = new NewsManagementService(postRepository);
        ManagerController managerController = new ManagerController(newsService);
        ManagerView managerView = new ManagerView();

        UserRepository userRepository = new UserRepository(Paths.get("account.ser"));
        LogRepository logRepository = new LogRepository();
        AdminService adminService = new AdminService(userRepository, logRepository);
        AdminController adminController = new AdminController(adminService);
        AdminView adminView = new AdminView();

        AuthService authService = new AuthService(userRepository);
        AuthController authController = new AuthController(authService);
        AuthView authView = new AuthView();

        Menu menu = new Menu();

        // auth commands
        menu.registerCommand("login", new LoginCommand(authController, authView, menu));
        menu.registerCommand("logout", new LogoutCommand(menu));

        // admin commands
        menu.registerCommand("getLogs", new GetLogsCommand(adminController, adminView));
        menu.registerCommand("createUser", new CreateUserCommand(adminController, adminView));
        menu.registerCommand("updateUser", new UpdateUserCommand(adminController, adminView));
        menu.registerCommand("deleteUser", new DeleteUserCommand(adminController, adminView));

        // manager commands
        menu.registerCommand("addNews", new AddNewsCommand(managerController, managerView));

        menu.run();
    }
}
