package main.java.kbtu.chill_guys.university_management_system.menu.teacher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;

import java.util.List;

public class PutMarkCommand implements Command {

    private final TeacherService teacherService = new TeacherService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        TeacherView view = ViewFactory.getTeacherView(currentLanguage);

        Teacher teacher = (Teacher) Menu.getInstance().getLoggedUser();

        Semester semester = view.selectSemester();
        if (semester == null) {
            view.showNoSemesterSelectedMessage();
            return;
        }

        List<Discipline> disciplines = teacherService.getDisciplines(teacher, semester);
        if (disciplines.isEmpty()) {
            view.showNoDisciplinesMessage(semester);
            return;
        }

        Discipline selectedDiscipline = view.selectDiscipline(disciplines);

        List<Student> students = teacherService.getStudents(teacher, semester, selectedDiscipline);
        if (students.isEmpty()) {
            view.showNoStudentsMessage(selectedDiscipline);
            return;
        }

        Student selectedStudent = view.selectStudent(students);

        LessonRecord record = view.createLessonRecord();
        teacherService.addLessonRecord(teacher, semester, selectedDiscipline, selectedStudent,
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());

        view.showRecordAddedMessage(record);
    }
}
