package main.java.kbtu.chill_guys.university_management_system.menu.AdminCommands;


import universityManagementSystem.controllers.AdminController;

public class CreateUserCommand {
    private AdminController controller;

    private AdminController getController() {
        return this.controller;
    }

    private AdminController setController(AdminController controller) {
        this.controller = controller;
        return controller;
    }

    public void execute() {

    }
}