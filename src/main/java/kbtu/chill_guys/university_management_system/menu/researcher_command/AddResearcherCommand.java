package main.java.kbtu.chill_guys.university_management_system.menu.researcher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

public class AddResearcherCommand implements Command {
    private final ResearcherService researcherService = ResearcherService.getInstance();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        ResearcherView view = ViewFactory.getResearcherView(language);
        User user = Menu.getInstance().getLoggedUser();

        if(user instanceof CanBeResearcher){
            boolean haveBecome = researcherService.addResearcher(user);
            if(haveBecome){
               view.displaySuccessBecomingResearcher();
            } else {
                view.displayAlreadyIsResearcher();
            }
        }else {
            view.displayInvalidUser();
        }
    }
}
