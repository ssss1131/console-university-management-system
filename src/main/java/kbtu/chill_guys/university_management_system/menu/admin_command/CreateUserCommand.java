package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;


import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;

import java.util.Map;

public class CreateUserCommand implements Command {
    private final AdminController controller = new AdminController();
    private final ResearcherService service = ResearcherService.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        AdminView view = ViewFactory.getAdminView(currentLanguage);

        Map<String, Object> data = view.getUserInput();

        User user = controller.createUser(data);

        if(user instanceof Researcher){
            service.addResearcher(user);
        }

        view.displayUserCreated(user);
    }

    public static void main(String[] args) {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.execute();
    }

}
