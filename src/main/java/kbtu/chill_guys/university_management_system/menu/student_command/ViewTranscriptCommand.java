package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.TranscriptService;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.storage.TeacherDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.storage.TranscriptStorage;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;
import java.util.Set;

public class ViewTranscriptCommand implements Command {
    private final TranscriptService transcriptService = new TranscriptService();
    private final TranscriptStorage transcriptStorage = TranscriptStorage.getInstance();
    private final StudentDisciplineStorage studentDisciplineStorage = StudentDisciplineStorage.getInstance();
    private final TeacherDisciplineStorage teacherDisciplineStorage = TeacherDisciplineStorage.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);

        Student student = (Student) Menu.getInstance().getLoggedUser();

        Set<Semester> semesters = studentDisciplineStorage.getSemesters(student);
        if (semesters.isEmpty()) {
            view.showNoSemesterSelectedMessage();
            return;
        }

        for (Semester semester : semesters) {
            view.showSemesterHeader(semester);

            List<Discipline> disciplines = studentDisciplineStorage.getDisciplines(student, semester);
            if (disciplines.isEmpty()) {
                view.showNoDisciplinesAvailableMessage();
                continue;
            }

            for (Discipline discipline : disciplines) {
                if (teacherDisciplineStorage.isAttestationClosed(discipline)) {
                    Transcript transcript = getTranscriptForDiscipline(student, discipline);
                    if (transcript != null) {
                        view.showTranscriptRecordWithGrades(transcript);
                    } else {
                        view.showDisciplineWithoutGrades(discipline);
                    }
                } else {
                    view.showDisciplineWithoutGrades(discipline);
                }
            }
        }
    }

    private Transcript getTranscriptForDiscipline(Student student, Discipline discipline) {
        List<Transcript> transcripts = transcriptStorage.getTranscriptsForStudent(student.getId());
        return transcripts.stream()
                .filter(t -> t.getSubject().equals(discipline))
                .findFirst()
                .orElse(null);
    }
}
