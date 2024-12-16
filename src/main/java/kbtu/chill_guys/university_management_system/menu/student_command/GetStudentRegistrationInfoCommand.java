package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineRegistrationService;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;

public class GetStudentRegistrationInfoCommand implements Command {

    private final StudentDisciplineStorage storage = StudentDisciplineStorage.getInstance();
    private final DisciplineRegistrationService disciplineRegistrationService = new DisciplineRegistrationService();
    private final StudentView view = new StudentView();

    @Override
    public void execute() {
        Student student = (Student) Menu.getInstance().getLoggedUser();

        Semester semester = disciplineRegistrationService.getSemester();
        List<Discipline> disciplines = storage.getDisciplines(student, semester);
        view.showRegisteredDisciplines(disciplines, semester);

    }
}
