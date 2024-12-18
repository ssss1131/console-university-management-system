package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineRegistrationService;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;

public class OpenRegistrationCommand implements Command {
    private final DisciplineRegistrationService service = new DisciplineRegistrationService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        Semester semester;
        if(!service.haveOpenedRegistration()){
            semester = view.getSemesterInput();
        }else {
            semester =service.getSemester();
            view.showSemesterInfo(semester);
        }
        StudentRole role = view.getStudentRoleInput();
        int course = view.getCourseInput();
        Program program = view.selectProgram(role);

        List<Discipline> availableDisciplines = service.getAvailableDisciplines(course, semester, role, program);

        List<Discipline> selectedDisciplines = view.selectDisciplinesForCourse(availableDisciplines);

        if (!selectedDisciplines.isEmpty()){
            service.openRegistration(course, role, semester, selectedDisciplines, program);
        } else {
            System.out.println("Cant open registration because none disciplines was selected");
        }
    }
}
