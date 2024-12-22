package main.java.kbtu.chill_guys.university_management_system.menu.teacher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.ComplaintService;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;

public class CreateComplaintCommand implements Command {
    private final TeacherService teacherService = new TeacherService();
    private final ComplaintService complaintService = new ComplaintService();

    @Override
    public void execute() {
        Teacher teacher = (Teacher) Menu.getInstance().getLoggedUser();
        TeacherView view = ViewFactory.getTeacherView(Menu.getInstance().getLanguage());

        Semester selectedSemester = view.selectSemester();

        Discipline discipline = view.selectDiscipline(teacherService.getDisciplines(teacher, selectedSemester));

        Student student = view.selectStudent(teacherService.getStudents(teacher, selectedSemester, discipline));

        String comment = view.getComment();
        UrgencyLevel urgencyLevel = view.selectUrgencyLevel();

        complaintService.createComplaint(teacher, student, discipline, comment, urgencyLevel);

        view.showComplaintCreatedMessage(discipline, student, urgencyLevel);
    }
}
