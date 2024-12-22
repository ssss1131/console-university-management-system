package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.OrganizationService;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

public class CreateOrganizationCommand implements Command {

    private final OrganizationService organizationService = new OrganizationService();

    @Override
    public void execute() {
        Menu menu = Menu.getInstance();
        Language language = menu.getLanguage();
        StudentView view = ViewFactory.getStudentView(language);
        String name = view.getOrganizationName();
        while (!organizationService.isUniqueName(name)){
            view.showAlreadyExistingOrganizationName();
            name = view.getOrganizationName();
        }
        String description = view.getOrganizationDescription();
        organizationService.save(new Organization(name, description), (Student) menu.getLoggedUser());
    }
}
