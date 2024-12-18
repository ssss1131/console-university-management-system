package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.Scanner;
import java.util.UUID;

public class JournalViewEn implements JournalView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getJournalName() {
        System.out.println("Enter journal name: ");
        return scanner.nextLine();
    }

    @Override
    public UUID getJournalId() {
        System.out.println("Enter journal ID: ");
        return UUID.fromString(scanner.nextLine());
    }

    @Override
    public Post getPostInput() {
        System.out.println("Enter post content: ");
        String content = scanner.nextLine();
        return new Post();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void postPublished() {
        System.out.println("Post published successfully!");
    }

    @Override
    public void journalCreated(UUID id) {
        System.out.println("Journal created successfully! UUID: " + id);
    }

    @Override
    public void journalDeleted() {
        System.out.println("Journal deleted successfully!");
    }
}