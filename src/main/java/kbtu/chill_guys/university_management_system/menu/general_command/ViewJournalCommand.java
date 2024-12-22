package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.storage.JournalStorage;
import main.java.kbtu.chill_guys.university_management_system.view.GeneralView;

import java.util.List;
import java.util.Set;

public class ViewJournalCommand implements Command {
    private final JournalStorage storage = JournalStorage.getInstance();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        GeneralView generalView = ViewFactory.getGeneralView(language);
        Set<Journal> journals =  storage.getJournals();
        Journal journal = generalView.selectJournal(journals);
        if(journal != null){
            List<ResearchPaper> papers = storage.getPapers(journal);
            List<ResearchProject> projects = storage.getProjects(journal);
            generalView.showPosts(papers, projects);
        }
    }
}
