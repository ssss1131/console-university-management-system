package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.storage.TeacherDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ViewStudentMarksCommand implements Command {

    private final StudentDisciplineStorage studentStorage = StudentDisciplineStorage.getInstance();
    private final TeacherDisciplineStorage teacherStorage = TeacherDisciplineStorage.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);
        Student student = (Student) Menu.getInstance().getLoggedUser();

        Set<Semester> semesters = studentStorage.getSemesters(student);
        Semester semester = view.getSemester(semesters);

        if (semester == null && semesters.isEmpty()) {
            view.showNoSemesterSelectedMessage();
            return;
        }

        if (semester == null) {
            processAllSemesters(view, student, semesters);
        } else {
            processSingleSemester(view, student, semester);
        }
    }

    private void processAllSemesters(StudentView view, Student student, Set<Semester> semesters) {
        for (Semester semester : semesters) {
            List<Discipline> disciplines = studentStorage.getDisciplines(student, semester);
            if (!disciplines.isEmpty()) {
                view.showSemesterHeader(semester);
                processDisciplines(view, student, semester, disciplines);
            }
        }
    }

    private void processSingleSemester(StudentView view, Student student, Semester semester) {
        List<Discipline> disciplines = studentStorage.getDisciplines(student, semester);
        if (disciplines.isEmpty()) {
            view.showNoDisciplinesAvailableMessage();
        } else {
            processDisciplines(view, student, semester, disciplines);
        }
    }

    private void processDisciplines(StudentView view, Student student, Semester semester, List<Discipline> disciplines) {
        for (Discipline discipline : disciplines) {
            view.showDiscipline(discipline);
            Map<Student, List<LessonRecord>> records = teacherStorage.getLessonRecordsForDiscipline(semester, discipline);

            if (records.containsKey(student)) {
                processLessonRecords(view, student, records.get(student));
            } else {
                view.showNoMarksMessage(discipline);
            }
        }
    }

    private void processLessonRecords(StudentView view, Student student, List<LessonRecord> records) {
        double totalMarks = 0.0;
        int totalPresence = 0;
        int totalAbsence = 0;

        view.showMarksHeader();
        for (LessonRecord record : records) {
            view.showMarkRow(record);
            totalMarks += record.getGrade();
            if (record.getAttendance() == Attendance.PRESENT) {
                totalPresence++;
            } else if (record.getAttendance() == Attendance.ABSENT) {
                totalAbsence++;
            }
        }

        view.showMarksFooter(totalMarks, totalPresence, totalAbsence);
    }
}
