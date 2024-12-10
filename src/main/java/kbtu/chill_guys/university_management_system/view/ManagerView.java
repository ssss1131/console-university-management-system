package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.time.LocalDate;
import java.util.Scanner;

public class ManagerView {
    private final Scanner scanner = new Scanner(System.in);

    public Post getPostInput() {
        Post post = new Post();

        System.out.println("Enter post title:");
        post.setTitle(scanner.nextLine());

        System.out.println("Enter post content:");
        post.setContent(scanner.nextLine());

        System.out.println("Enter author name:");
//        User author = new User(); // Укажите текущего пользователя
//        author.setFirstName(scanner.nextLine());
//        post.setAuthor(author);

        post.setDate(LocalDate.now());
        return post;
    }

    public void displayPostAdded(Post post) {
        System.out.println("Post added successfully:");
        System.out.println(post);
    }
}
