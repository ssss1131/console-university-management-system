package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.Subscriber;
import main.java.kbtu.chill_guys.university_management_system.model.UserSubscriber;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.Vector;
import java.util.UUID;

public class JournalService {
    private final Vector<Journal> journals;

    public JournalService() {
        this.journals = new Vector<>();
    }

    public boolean createJournal(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        Journal journal = new Journal(name, UUID.randomUUID());
        journals.add(journal);  // Добавляем журнал в список
        return true;
    }

    public boolean deleteJournal(String name) {
        for (Journal journal : journals) {
            if (journal.getName().equals(name)) {
                journals.remove(journal);  // Удаляем журнал из списка
                return true;
            }
        }
        return false;
    }

    public boolean addSubscriber(String journalName, UserSubscriber subscriber) {
        for (Journal journal : journals) {
            if (journal.getName().equals(journalName)) {
                return journal.addSubscriber(subscriber);  // Добавляем подписчика в журнал
            }
        }
        return false;
    }

    public boolean removeSubscriber(String journalName, UserSubscriber subscriber) {
        for (Journal journal : journals) {
            if (journal.getName().equals(journalName)) {
                return journal.removeSubscriber(subscriber);  // Удаляем подписчика из журнала
            }
        }
        return false;
    }

    public void publishPost(String journalName, Post post) {
        for (Journal journal : journals) {
            if (journal.getName().equals(journalName)) {
                journal.publish(post);  // Публикуем пост для всех подписчиков
                return;
            }
        }
    }
}
