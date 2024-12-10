package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.repository.JournalRepository;

import java.util.List;
import java.util.UUID;

public class JournalService {
    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public void createJournal(Journal journal) {
        journalRepository.save(journal);
    }

    public void deleteJournal(UUID id) {
        journalRepository.delete(id);
    }

    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    public void publishPost(UUID journalId, Post post) {
        Journal journal = journalRepository.findById(journalId);
        if (journal != null) {
            journal.publish(post);
            journalRepository.save(journal);
        } else {
            throw new IllegalArgumentException("Journal not found.");
        }
    }
}
