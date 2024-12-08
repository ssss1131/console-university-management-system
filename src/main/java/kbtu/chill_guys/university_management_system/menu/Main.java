package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.service.AuthService;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository(Paths.get("users.db"));
        AdminService adminService = new AdminService(userRepository);
        AdminController adminController = new AdminController(adminService);
        AdminView adminView = new AdminView();

        AuthService loginService = new AuthService(userRepository);
        AuthController loginController = new AuthController(loginService);
        AuthView loginView = new AuthView();

        Menu menu = new Menu();

        menu.registerCommand("createUser", new CreateUserCommand(adminController, adminView));
        menu.registerCommand("login", new LoginCommand(loginController, loginView));

        menu.run();
    }
}
