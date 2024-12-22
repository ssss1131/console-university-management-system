package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.Set;

public class ViewStudentDisciplinesCommand implements Command {
    private final StudentDisciplineStorage storage = StudentDisciplineStorage.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);
        Student student = (Student) Menu.getInstance().getLoggedUser();
        Set<Semester> semesters = storage.getSemesters(student);
        Semester semester = view.getSemester(semesters);
        if (semester == null){
            view.showAllDisciplines(storage.getAllDisciplines(student));
        }else {
            view.showExactDisciplines(storage.getDisciplines(student, semester));
        }
    }
}
