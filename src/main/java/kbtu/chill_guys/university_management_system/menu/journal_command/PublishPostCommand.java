package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

public class PublishPostCommand implements Command {
    private final JournalController journalController;
    private final Journal journal;
    private final Post post;
    private final JournalView journalView;

    public PublishPostCommand(JournalController journalController, Journal journal, Post post, JournalView journalView) {
        this.journalController = journalController;
        this.journal = journal;
        this.post = post;
        this.journalView = journalView;
    }

    @Override
    public void execute() {
        try {
            journalController.publishPost(journal.getName(), post);
            System.out.println("Post published successfully!");
            journalView.displayAllPosts(journal);
        } catch (Exception e) {
            System.out.println("An error occurred while publishing the post: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
