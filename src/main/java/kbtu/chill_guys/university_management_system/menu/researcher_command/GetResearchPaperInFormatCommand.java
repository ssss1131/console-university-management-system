package main.java.kbtu.chill_guys.university_management_system.menu.researcher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Format;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

import java.util.List;

public class GetResearchPaperInFormatCommand implements Command {

    private final ResearcherService service  = ResearcherService.getInstance();
    private ResearcherView view;

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        view = ViewFactory.getResearcherView(language);

        User user = Menu.getInstance().getLoggedUser();
        List<ResearchPaper> papers = service.getResearchPapers(user);
        ResearchPaper researchPaper = view.selectResearchPaper(papers);
        Format format = view.selectCitationFormat();
        view.displayCitation(researchPaper, format);

    }
}
