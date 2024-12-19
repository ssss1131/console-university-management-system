package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

public class AddResearcherCommand implements Command {

    private final ResearcherService researcherService =ResearcherService.getInstance();
    private final ResearcherView view = new ResearcherView();

    @Override
    public void execute() {
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
