package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.menu.AdminCommands.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService(new UserRepository(Paths.get("users.db")));
        AdminController adminController = new AdminController(adminService);
        AdminView adminView = new AdminView();

        Menu menu = new Menu();

        menu.registerCommand("createUser", new CreateUserCommand(adminController, adminView));

        menu.run();
    }
}
