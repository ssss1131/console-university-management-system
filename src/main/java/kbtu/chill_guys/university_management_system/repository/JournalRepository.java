package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;

import java.nio.file.Path;
import java.util.UUID;
import java.util.Vector;

public class JournalRepository extends AbstractRepository<Journal> {
    public JournalRepository(Path path) {
        super(path);
    }

    public Journal findById(UUID id) {
        return getAllLines().stream()
                .filter(journal -> journal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Journal journal) {
        Vector<Journal> journals = getAllLines();
        journals.removeIf(existingJournal -> existingJournal.getId().equals(journal.getId()));
        journals.add(journal);
        saveData(journals);
    }

    public void delete(UUID id) {
        Vector<Journal> journals = getAllLines();
        journals.removeIf(journal -> journal.getId().equals(id));
        saveData(journals);
    }

    public Vector<Journal> findAll() {
        return getAllLines();
    }
}
