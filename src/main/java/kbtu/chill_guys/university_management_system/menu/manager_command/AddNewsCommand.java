package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.controller.ManagerController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.Map;

public class AddNewsCommand implements Command {
    private final ManagerController controller = new ManagerController();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        Map<String, Object> data = view.getPostInput();

        if (data == null) {
            System.out.println("Failed to create post. Please ensure you are logged in.");
            return;
        }

        Post post = controller.createPost(data);

        view.displayPostAdded(post);
    }
}
