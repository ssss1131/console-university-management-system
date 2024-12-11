package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.time.LocalDate;
import java.util.Scanner;

public class ManagerView {
    private final Scanner scanner = new Scanner(System.in);

    public void addNewCourses(){

    }


    public void displayCoursesForRegistration() {
        //TODO
    }

    public Post getPostInput() {
        Post post = new Post();

        System.out.println("Enter post title:");
        post.setTitle(scanner.nextLine());

        System.out.println("Enter post content:");
        post.setContent(scanner.nextLine());

        User loggedUser = Menu.getInstance().getLoggedUser();
        if (loggedUser != null) {
            post.setAuthor(loggedUser);
        } else {
            System.out.println("No logged-in user found. Unable to set author.");
        }

        post.setDate(LocalDate.now());
        return post;
    }

    public void displayPostAdded(Post post) {
        System.out.println("Post added successfully:");
        System.out.println(post);
    }
}
