package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

public class GetTopCitedResearcherBySchoolCommand implements Command {

    private final ResearcherService service = ResearcherService.getInstance();
    private ResearcherView view;

    @Override
    public void execute() {
        School school = view.getSchool();

        service.getTopCitedResearcherBySchool(school);
    }
}
