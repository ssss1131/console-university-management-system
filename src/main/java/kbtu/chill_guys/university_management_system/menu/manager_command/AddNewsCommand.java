package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.controller.ManagerController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

public class AddNewsCommand implements Command {
    private final ManagerController controller = new ManagerController();
    private final ManagerView view = new ManagerView();

    @Override
    public void execute() {
        Post post = view.getPostInput();
        controller.addNews(post);
        view.displayPostAdded(post);
    }
}
