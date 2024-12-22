package main.java.kbtu.chill_guys.university_management_system.menu.journal_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.JournalService;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.UUID;

public class CreateJournalCommand implements Command {
    private final JournalService journalService = new JournalService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        JournalView view = ViewFactory.getJournalView(currentLanguage);

        String name = view.getNewJournalName();
        Journal journal = new Journal(name, UUID.randomUUID());
        journalService.createJournal(journal);
        view.journalCreated(journal.getId());
    }
}


