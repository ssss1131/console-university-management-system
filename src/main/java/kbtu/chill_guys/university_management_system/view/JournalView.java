package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.UUID;

public interface JournalView {
    String getJournalName();

    UUID getJournalId();

    Post getPostInput();

    void displayMessage(String message);

    void postPublished();

    void journalCreated(UUID id);

    void journalDeleted();
}
