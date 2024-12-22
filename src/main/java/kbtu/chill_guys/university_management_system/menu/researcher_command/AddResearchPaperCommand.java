package main.java.kbtu.chill_guys.university_management_system.menu.researcher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

public class AddResearchPaperCommand implements Command {
    private final ResearcherService researcherService = ResearcherService.getInstance();
    private final UserRepository userRepository = new UserRepository();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        ResearcherView view = ViewFactory.getResearcherView(language);
        User user = Menu.getInstance().getLoggedUser();

        ResearchPaper researchPaper = view.getResearchPaper();

        researcherService.addNewResearchPaper(researchPaper);
        if(user instanceof Researcher researcher){
            researcher.addResearchPaper(researchPaper);
            userRepository.update(user);
        }
    }
}
