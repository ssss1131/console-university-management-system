package main.java.kbtu.chill_guys.university_management_system.menu.AdminCommands;


import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.Map;

public class CreateUserCommand implements Command {
    private final AdminController controller;
    private final AdminView view;

    public CreateUserCommand(AdminController controller, AdminView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        System.out.println("Executing CreateUserCommand...");
        Map<String, Object> data = view.getUserInput();

        User user = controller.createUser(data);

        view.displayUserCreated(user);
    }
}
