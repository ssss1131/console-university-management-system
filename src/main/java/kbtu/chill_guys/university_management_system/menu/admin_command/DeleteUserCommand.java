package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.UUID;

public class DeleteUserCommand implements Command {
    private final AdminController controller;
    private final AdminView view;

    public DeleteUserCommand(AdminController controller, AdminView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        UUID userId = view.getUserIdForDeletion();

        if (!controller.isExistingUser(userId)) {
            view.displayMessage("User not found!");
            return;
        }

        controller.removeUser(userId);

        view.displayMessage("User deleted successfully!");
    }
}
