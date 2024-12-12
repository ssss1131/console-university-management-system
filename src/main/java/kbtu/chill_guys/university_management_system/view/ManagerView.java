package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public class ManagerView {
    private final Scanner scanner = new Scanner(System.in);

    public void addNewCourses(){

    }


    public void displayCoursesForRegistration() {
        //TODO
    }

    public Map<String, Object> getPostInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Enter post title:");
        data.put(TITLE_ATTRIBUTE, InputValidatorUtil.validateNonEmptyInput("Post title cannot be empty."));

        System.out.println("Enter post content:");
        data.put(CONTENT_ATTRIBUTE, InputValidatorUtil.validateNonEmptyInput("Post content cannot be empty."));

        User loggedUser = Menu.getInstance().getLoggedUser();
        if (loggedUser != null && loggedUser.getRole() == UserRole.MANAGER) {
            data.put(AUTHOR_ATTRIBUTE, loggedUser);
        } else {
            System.out.println("Only managers can add posts. Unable to set author.");
            return null;
        }

        data.put(DATE_ATTRIBUTE, LocalDate.now());

        return data;
    }

    public void displayPostAdded(Post post) {
        if (post != null) {
            System.out.println("Post added successfully!");
            System.out.println(post);
        } else {
            System.out.println("Failed to add post.");
        }
    }
}
