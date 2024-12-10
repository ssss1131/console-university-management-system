package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.util.Constant;

import java.nio.file.Path;
import java.util.UUID;
import java.util.Vector;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.JOURNAL_PATH;

public class JournalRepository extends AbstractRepository<Journal> {
    public JournalRepository() {
        super(JOURNAL_PATH);
    }

    public Journal findById(UUID id) {
        return getAllLines().stream()
                .filter(journal -> journal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Journal journal) {
        addLine(journal);
    }

    public void delete(UUID id) {
        Vector<Journal> journals = getAllLines();
        journals.removeIf(journal -> journal.getId().equals(id));
        saveAllLines(journals);
    }

    public Vector<Journal> findAll() {
        return getAllLines();
    }
}
