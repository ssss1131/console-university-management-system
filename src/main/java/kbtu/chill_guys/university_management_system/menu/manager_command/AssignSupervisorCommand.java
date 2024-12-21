package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;

public class AssignSupervisorCommand implements Command {

    private final UserRepository userRepository = new UserRepository();
    private ManagerView view;

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        view = ViewFactory.getManagerView(language);
        List<GraduateStudent> students = userRepository.getAllLines().stream()
                .filter(user -> user instanceof GraduateStudent)
                .map(user -> (GraduateStudent) user)
                .filter(graduateStudent -> graduateStudent.getProject().getSupervisor() == null)
                .toList();
        List<ResearchSupervisor> researchSupervisors = userRepository.getAllLines().stream()
                .filter(user -> user instanceof ResearchSupervisor)
                .map(user -> (ResearchSupervisor) user)
                .toList();

        GraduateStudent student = view.showFreeStudents(students);

        ResearchSupervisor supervisor = view.showSupervisors(researchSupervisors);
        student.getProject().setSupervisor(supervisor);
        userRepository.update(student);
    }
}
