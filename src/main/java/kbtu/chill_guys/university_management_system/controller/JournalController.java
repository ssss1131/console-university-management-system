package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.Subscriber;
import main.java.kbtu.chill_guys.university_management_system.model.UserSubscriber;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;

public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    public boolean createJournal(String name) {
        return journalService.createJournal(name);
    }

    public boolean deleteJournal(String name) {
        return journalService.deleteJournal(name);
    }

    public boolean addSubscriber(String journalName, UserSubscriber subscriber) {
        return journalService.addSubscriber(journalName, subscriber);
    }

    public boolean removeSubscriber(String journalName, UserSubscriber subscriber) {
        return journalService.removeSubscriber(journalName, subscriber);
    }

    public void publishPost(String journalName, Post post) {
        journalService.publishPost(journalName, post);
    }
}
