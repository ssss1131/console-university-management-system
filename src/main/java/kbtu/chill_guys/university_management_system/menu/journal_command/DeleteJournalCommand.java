package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.service.JournalService;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;

public class DeleteJournalCommand implements Command {
    private final JournalService journalService;
    private final String journalName;

    public DeleteJournalCommand(JournalService journalService, String journalName) {
        this.journalService = journalService;
        this.journalName = journalName;
    }

    @Override
    public void execute() {
        try {
            boolean result = journalService.deleteJournal(journalName);
            if (result) {
                System.out.println("Journal '" + journalName + "' deleted successfully!");
            } else {
                System.out.println("Failed to delete journal '" + journalName + "'.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the journal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
