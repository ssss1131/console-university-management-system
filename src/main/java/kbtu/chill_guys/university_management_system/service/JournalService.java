package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.storage.JournalStorage;

import java.util.ArrayList;
import java.util.List;

public class JournalService {

    private final JournalStorage journalStorage = JournalStorage.getInstance();

    public void createJournal(Journal journal) {
        journalStorage.addNewJournal(journal);
    }

    public void deleteJournal(Journal journal){
        journalStorage.delete(journal);
    }

    public List<Journal> getAllJournals() {
        return new ArrayList<>(journalStorage.getPapersByJournal().keySet());
    }
}
