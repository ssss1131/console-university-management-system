package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.OrganizationService;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;

public class ViewOrganizationInfoCommand implements Command {

    private final OrganizationService service = new OrganizationService();


    @Override
    public void execute() {
        Menu menu = Menu.getInstance();
        Student student = (Student) menu.getLoggedUser();
        StudentView studentView = ViewFactory.getStudentView(menu.getLanguage());
        List<Organization> organization  = service.findOrganization(student);
        studentView.showOrganizationInfo(organization);
    }
}
