package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

import java.time.LocalDate;
import java.util.Map;

public class AddNewsCommand implements Command {
    private final NewsManagementService newsManagementService = new NewsManagementService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        Map<String, Object> data = view.getPostInput();

        if (data == null) {
            System.out.println("Failed to create post. Please ensure you are logged in.");
            return;
        }

        Post post = new Post();
        post.setTitle((String) data.get(TITLE_ATTRIBUTE));
        post.setContent((String) data.get(CONTENT_ATTRIBUTE));
        post.setAuthor((User) data.get(AUTHOR_ATTRIBUTE));
        post.setDate((LocalDate) data.get(DATE_ATTRIBUTE));

        newsManagementService.addPost(post);

        view.displayPostAdded(post);
    }
}
