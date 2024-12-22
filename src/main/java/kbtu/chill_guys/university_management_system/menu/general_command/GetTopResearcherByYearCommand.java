package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

import java.util.List;

public class GetTopResearcherByYearCommand implements Command {
    private final ResearcherService service = ResearcherService.getInstance();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        ResearcherView view = ViewFactory.getResearcherView(language);

        List<Integer> allPublicationYears = service.getAllPublicationYears();

        int year = view.selectPublicationYear(allPublicationYears);
        if(allPublicationYears != null && !allPublicationYears.isEmpty()){
            User user = service.getTopCitedResearcherByYear(year);
            int totalCitations = service.calculateTotalCitations(user);
            view.showTopCitedResearcher(user, totalCitations, year);
        }
    }
}
