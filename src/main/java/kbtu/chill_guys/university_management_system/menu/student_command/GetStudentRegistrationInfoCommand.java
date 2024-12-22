package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineRegistrationService;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;

public class GetStudentRegistrationInfoCommand implements Command {
    private final StudentDisciplineStorage storage = StudentDisciplineStorage.getInstance();
    private final DisciplineRegistrationService disciplineRegistrationService = new DisciplineRegistrationService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);

        Student student = (Student) Menu.getInstance().getLoggedUser();

        Semester semester = disciplineRegistrationService.getSemester();
        if(semester!=null){
            List<Discipline> disciplines = storage.getDisciplines(student, semester);
            view.showRegisteredDisciplines(disciplines, semester);
        }else{
//            view.showClosedRegistrationInfo(); TODO надо добавить везде его
        }

    }
}
