package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;

import java.util.List;
import java.util.UUID;

public interface JournalView {
    String getNewJournalName();

    Journal getJournalForDeletion(List<Journal> journals);

    void journalCreated(UUID id);

    void journalDeleted();
}
