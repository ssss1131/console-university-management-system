package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.Subscriber;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

public class RemoveSubscriberCommand implements Command {
    private final JournalController journalController;
    private final Journal journal;
    private final Subscriber subscriber;
    private final JournalView journalView;

    public RemoveSubscriberCommand(JournalController journalController, Journal journal, Subscriber subscriber, JournalView journalView) {
        this.journalController = journalController;
        this.journal = journal;
        this.subscriber = subscriber;
        this.journalView = journalView;
    }

    @Override
    public void execute() {
        try {
            boolean result = journalController.removeSubscriber(journal.getName(), subscriber);
            if (result) {
                System.out.println("Subscriber removed successfully!");
                journalView.displaySubscribers(journal);
            } else {
                System.out.println("Failed to remove subscriber.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while removing the subscriber: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
