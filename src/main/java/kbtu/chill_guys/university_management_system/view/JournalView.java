package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.Scanner;
import java.util.UUID;

public class JournalView {
    private final Scanner scanner = new Scanner(System.in);

    public String getJournalName() {
        System.out.println("Enter journal name: ");
        return scanner.nextLine();
    }

    public UUID getJournalId() {
        System.out.println("Enter journal ID: ");
        return UUID.fromString(scanner.nextLine());
    }

    public Post getPostInput() {
        System.out.println("Enter post content: ");
        String content = scanner.nextLine();
        return new Post(); // assuming Post has a constructor that accepts content
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}