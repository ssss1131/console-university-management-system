package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.UUID;

public class PublishPostCommand implements Command {
    private final JournalController controller = new JournalController();
    private final JournalView view = new JournalView();

    @Override
    public void execute() {
        UUID journalId = view.getJournalId();
        Post post = view.getPostInput();
        controller.publishPost(journalId, post);
        view.displayMessage("Post published successfully!");
    }
}


