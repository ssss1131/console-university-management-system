package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

public class CreateJournalCommand implements Command {
    private final JournalController controller;
    private final JournalView view;

    public CreateJournalCommand(JournalController controller, JournalView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        String name = view.getJournalName();
        Journal journal = new Journal(name, null);  // ID будет сгенерирован
        controller.createJournal(journal);
        view.displayMessage("Journal created successfully! UUID: " + journal.getId());
    }
}


