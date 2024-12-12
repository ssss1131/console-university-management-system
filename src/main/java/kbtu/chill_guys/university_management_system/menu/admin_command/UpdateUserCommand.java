package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.Map;

public class UpdateUserCommand implements Command {
    private final AdminController controller = new AdminController();
    private final AdminView view = new AdminView();


    @Override
    public void execute() {
        Map<String, Object> data = view.getUserInput();

        controller.modifyUser(data);

        view.displayMessage("User updated successfully!");
    }
}