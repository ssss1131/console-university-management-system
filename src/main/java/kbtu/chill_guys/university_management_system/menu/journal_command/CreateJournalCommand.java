package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

public class CreateJournalCommand implements Command {
    private final JournalController journalController;
    private final Journal journal;
    private final JournalView journalView;

    public CreateJournalCommand(JournalController journalController, Journal journal, JournalView journalView) {
        this.journalController = journalController;
        this.journal = journal;
        this.journalView = journalView;
    }

    @Override
    public void execute() {
        try {
            boolean result = journalController.createJournal(journal.getName());
            if (result) {
                System.out.println("Journal '" + journal.getName() + "' created successfully!");
                journalView.displayJournalDetails(journal);
            } else {
                System.out.println("Failed to create journal '" + journal.getName() + "'.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the journal: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
