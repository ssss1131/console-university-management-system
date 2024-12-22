package main.java.kbtu.chill_guys.university_management_system.menu.researcher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

import java.util.Comparator;
import java.util.List;

public class GetSortedAllResearchPapersCommand implements Command {
    private final ResearcherService service  = ResearcherService.getInstance();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        ResearcherView view = ViewFactory.getResearcherView(language);

        Comparator<ResearchPaper> comparator = view.getSortPreference();
        List<ResearchPaper> papers = service.getResearchPapers();
        papers.sort(comparator);
        view.displayResearchPapers(papers);
    }
}
