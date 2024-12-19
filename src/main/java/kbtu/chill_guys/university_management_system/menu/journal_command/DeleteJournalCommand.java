package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.controller.JournalController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.UUID;

public class DeleteJournalCommand implements Command {
    private final JournalController controller = new JournalController();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        JournalView view = ViewFactory.getJournalView(currentLanguage);

        UUID journalId = view.getJournalId();
        controller.deleteJournal(journalId);
        view.journalDeleted();
    }
}


