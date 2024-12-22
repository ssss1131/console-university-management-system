package main.java.kbtu.chill_guys.university_management_system.menu.teacher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TraditionalGrade;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;
import main.java.kbtu.chill_guys.university_management_system.service.TranscriptService;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;

import java.util.List;

public class CloseAttestationCommand implements Command {
    private final TeacherService teacherService = new TeacherService();
    private final TranscriptService transcriptService = new TranscriptService();

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

        if (!view.confirmAttestationClosure()) {
            System.out.println("Attestation closure cancelled.");
            return;
        }

        teacherService.closeAttestation(selectedDiscipline);

        List<Student> students = teacherService.getStudents(teacher, semester, selectedDiscipline);
        for (Student student : students) {
            List<LessonRecord> records = teacherService.getLessonRecords(teacher, semester, selectedDiscipline, student);
            double totalGrade = computeTotalGrade(records);
            String gpaLetter = transcriptService.convertToGpaLetter(totalGrade);
            TraditionalGrade traditionalGrade = transcriptService.getTraditionalGrade(totalGrade);

            Transcript transcript = new Transcript();
            transcript.setStudent(student);
            transcript.setSubject(selectedDiscipline);
            transcript.setYear(semester.getYear());
            transcript.setPeriod(semester.getPeriod());
            transcript.setGpaNumeric((int) totalGrade);
            transcript.setTotalGrade(totalGrade);
            transcript.setGpaTraditional(traditionalGrade.name());
            transcriptService.saveTranscript(transcript);

            view.showTranscriptUpdatedMessage(student, selectedDiscipline, transcript);
        }

        view.showAttestationClosedMessage(selectedDiscipline, semester);
    }

    private double computeTotalGrade(List<LessonRecord> records) {
        double total = 0;
        int count = 0;
        for (LessonRecord record : records) {
            if (record.getAttendance() == Attendance.PRESENT) {
                total += record.getGrade();
                count++;
            }
        }
        return count > 0 ? total / count : 0.0;
    }
}
