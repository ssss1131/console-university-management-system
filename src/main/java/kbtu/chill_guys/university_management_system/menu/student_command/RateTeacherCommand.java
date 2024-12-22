package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;

public class RateTeacherCommand implements Command {
    private final TeacherService teacherService = new TeacherService();
    private final UserRepository userRepository = new UserRepository();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);

        Student student = (Student) Menu.getInstance().getLoggedUser();

        List<Teacher> teachers = teacherService.getAllTeachers();
        if (teachers.isEmpty()) {
            view.showNoTeachersAvailableMessage();
            return;
        }

        Teacher selectedTeacher = view.selectTeacher(teachers);
        if (selectedTeacher == null) {
            System.out.println("No teacher selected.");
            return;
        }

        int rating = view.getTeacherRating();

        teacherService.updateTeacherRating(selectedTeacher, rating);

        view.showTeacherRatedMessage(selectedTeacher, rating);
    }
}
