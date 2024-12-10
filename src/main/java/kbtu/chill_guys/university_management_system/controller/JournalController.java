package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;

import java.util.List;
import java.util.UUID;

public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    public void createJournal(Journal journal) {
        journalService.createJournal(journal);
    }

    public void deleteJournal(UUID id) {
        journalService.deleteJournal(id);
    }

    public List<Journal> getAllJournals() {
        return journalService.getAllJournals();
    }

    public void publishPost(UUID journalId, Post post) {
        journalService.publishPost(journalId, post);
    }
}
