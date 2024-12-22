package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;

import java.time.LocalDate;
import java.util.List;

public class TeacherViewEn implements TeacherView {
    @Override public Semester selectSemester() {
       System.out.println("Select semester:");
       int year = InputValidatorUtil.validateIntegerInput("Enter a valid year:", 2000, 2100);
       Period period = EnumSelectionUtil.selectEnum(Period.class);
       return new Semester(year, period);
    }

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("No semester selected. Returning to the main menu.");
    }

    @Override
    public Discipline selectDiscipline(List<Discipline> disciplines) {
        System.out.println("Select a discipline:");
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, disciplines.get(i).getName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Invalid selection. Please try again.", 1, disciplines.size());
        return disciplines.get(choice - 1);
    }

    @Override
    public Student selectStudent(List<Student> students) {
        System.out.println("Select a student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s %s%n", i + 1, students.get(i).getFirstName(), students.get(i).getLastName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Invalid selection. Please try again.", 1, students.size());
        return students.get(choice - 1);
    }

    @Override
    public LessonRecord createLessonRecord() {
        System.out.println("Enter lesson name:");
        String lesson = InputValidatorUtil.validateNonEmptyInput("Lesson name cannot be empty. Please try again.");

        String dateInput = InputValidatorUtil.validateDateInput("Invalid date format. Please use yyyy-MM-dd.");
        LocalDate date = LocalDate.parse(dateInput);

        System.out.println("Select attendance:");
        Attendance attendance = EnumSelectionUtil.selectEnum(Attendance.class);

        Double grade = null;
        if (attendance != Attendance.ABSENT) {
            System.out.println("Enter grade (0-100):");
            grade = (double) InputValidatorUtil.validateIntegerInput("Grade must be between 0 and 100.", 0, 100);
        }

        System.out.println("Enter comment (optional, press Enter to skip):");
        String comment = InputValidatorUtil.validateOptionalInput();

        return new LessonRecord(date, lesson, attendance, grade != null ? grade : 0.0, comment);
    }


    @Override
    public void showNoDisciplinesMessage(Semester semester) {
        System.out.println("No disciplines found for semester: " + semester);
    }

    @Override
    public void showNoStudentsMessage(Discipline discipline) {
        System.out.println("No students found for discipline: " + discipline.getName());
    }

    @Override
    public void showRecordAddedMessage(LessonRecord record) {
        System.out.println("Record added successfully: " + record);
    }

    @Override
    public void showStudentRecords(Student student, List<LessonRecord> lessonRecords) {
        System.out.printf("=== Records for student: %s %s ===%n", student.getFirstName(), student.getLastName());
        if (lessonRecords.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        System.out.println("Date       | Lesson         | Attendance | Grade | Comments");
        System.out.println("-----------------------------------------------------------");
        for (LessonRecord record : lessonRecords) {
            System.out.printf("%s | %-14s | %-10s | %-5.2f | %s%n",
                    record.getDate(),
                    record.getLesson(),
                    record.getAttendance(),
                    record.getGrade(),
                    record.getComment());
        }
        System.out.println("-----------------------------------------------------------");
    }

    @Override
    public void showTranscriptUpdatedMessage(Student student, Discipline discipline, Transcript transcript) {
        System.out.printf(
                "Transcript updated for student %s %s in discipline %s: Total Grade: %.2f, GPA: %s, Traditional Grade: %s%n",
                student.getFirstName(),
                student.getLastName(),
                discipline.getName(),
                transcript.getTotalGrade(),
                transcript.getGpaLetter(),
                transcript.getGpaTraditional()
        );
    }

    @Override
    public void showAttestationClosedMessage(Discipline discipline, Semester semester) {
        System.out.printf("Attestation for discipline %s in semester %s has been successfully closed.%n",
                discipline.getName(),
                semester
        );
    }

    @Override
    public boolean confirmAttestationClosure() {
        System.out.println("Are you sure you want to close the attestation for this discipline? (yes/no):");
        String input = InputValidatorUtil.validateNonEmptyInput("Please enter 'yes' or 'no'.");
        return input.equalsIgnoreCase("yes");
    }

}
