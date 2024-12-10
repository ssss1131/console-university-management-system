package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.controller.ManagerController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

public class AddNewsCommand implements Command {
    private final ManagerController controller;
    private final ManagerView view;

    public AddNewsCommand(ManagerController controller, ManagerView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        Post post = view.getPostInput();
        controller.addNews(post);
        view.displayPostAdded(post);
    }
}
