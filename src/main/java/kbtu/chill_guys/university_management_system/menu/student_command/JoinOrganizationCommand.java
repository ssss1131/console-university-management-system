package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.OrganizationService;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;
import java.util.Set;

public class JoinOrganizationCommand implements Command {

    private final OrganizationService service = new OrganizationService();

    @Override
    public void execute() {
        Menu menu = Menu.getInstance();
        Student user = (Student) menu.getLoggedUser();
        StudentView view = ViewFactory.getStudentView(menu.getLanguage());
        List<Organization> organizations = service.getAllNotJoinedOrganizations(user);
        Organization organization = view.selectOrganization(organizations);
        if (organization != null) {
            List<OrganizationRole> availableRoles = service.getAvailableRoles(organization);
            OrganizationRole role = view.selectOrganizationRole(availableRoles);
            service.addNewMemberToOrganization(user, organization, role);

        }
    }
}
