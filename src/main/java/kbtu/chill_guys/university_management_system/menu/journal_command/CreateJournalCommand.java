package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

public class CreateJournalCommand implements Command {
    private final JournalController controller = new JournalController();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        JournalView view = ViewFactory.getJournalView(currentLanguage);

        String name = view.getJournalName();
        Journal journal = new Journal(name, null);
        controller.createJournal(journal);
        view.journalCreated(journal.getId());
    }
}


