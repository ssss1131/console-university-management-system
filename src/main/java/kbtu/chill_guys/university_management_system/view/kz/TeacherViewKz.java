package main.java.kbtu.chill_guys.university_management_system.view.kz;

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

public class TeacherViewKz implements TeacherView {
    @Override
    public Semester selectSemester() {
        System.out.println("Семестрді таңдаңыз:");
        int year = InputValidatorUtil.validateIntegerInput("Жарамды жылды енгізіңіз:", 2000, 2100);
        Period period = EnumSelectionUtil.selectEnum(Period.class);
        return new Semester(year, period);
    }

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("Семестр таңдалмады. Негізгі мәзірге оралу.");
    }

    @Override
    public Discipline selectDiscipline(List<Discipline> disciplines) {
        System.out.println("Пәнді таңдаңыз:");
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, disciplines.get(i).getName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Қате таңдау. Қайталап көріңіз.", 1, disciplines.size());
        return disciplines.get(choice - 1);
    }

    @Override
    public Student selectStudent(List<Student> students) {
        System.out.println("Студентті таңдаңыз:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s %s%n", i + 1, students.get(i).getFirstName(), students.get(i).getLastName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Қате таңдау. Қайталап көріңіз.", 1, students.size());
        return students.get(choice - 1);
    }

    @Override
    public LessonRecord createLessonRecord() {
        System.out.println("Сабақ атауын енгізіңіз:");
        String lesson = InputValidatorUtil.validateNonEmptyInput("Сабақ атауы бос болмауы керек. Қайта енгізіңіз.");

        String dateInput = InputValidatorUtil.validateDateInput("Күні қате форматта енгізілді. yyyy-MM-dd форматында пайдаланыңыз.");
        LocalDate date = LocalDate.parse(dateInput);

        System.out.println("Қатысу түрін таңдаңыз:");
        Attendance attendance = EnumSelectionUtil.selectEnum(Attendance.class);

        Double grade = null;
        if (attendance != Attendance.ABSENT) {
            System.out.println("Бағаны енгізіңіз (0-100):");
            grade = (double) InputValidatorUtil.validateIntegerInput("Баға 0 мен 100 арасында болуы керек.", 0, 100);
        }

        System.out.println("Пікір жазыңыз (міндетті емес, өткізіп жіберу үшін Enter басыңыз):");
        String comment = InputValidatorUtil.validateOptionalInput();

        return new LessonRecord(date, lesson, attendance, grade != null ? grade : 0.0, comment);
    }


    @Override
    public void showNoDisciplinesMessage(Semester semester) {
        System.out.println("Бұл семестрге пәндер жоқ: " + semester);
    }

    @Override
    public void showNoStudentsMessage(Discipline discipline) {
        System.out.println("Бұл пәнге студенттер жоқ: " + discipline.getName());
    }

    @Override
    public void showRecordAddedMessage(LessonRecord record) {
        System.out.println("Жазба сәтті қосылды: " + record);
    }

    @Override
    public void showStudentRecords(Student student, List<LessonRecord> lessonRecords) {
        System.out.printf("=== Студент жазбалары: %s %s ===%n", student.getFirstName(), student.getLastName());
        if (lessonRecords.isEmpty()) {
            System.out.println("Жазбалар жоқ.");
            return;
        }

        System.out.println("Күні       | Сабақ          | Қатысу     | Баға   | Пікірлер");
        System.out.println("----------------------------------------------------------");
        for (LessonRecord record : lessonRecords) {
            System.out.printf("%s | %-14s | %-10s | %-5.2f | %s%n",
                    record.getDate(),
                    record.getLesson(),
                    record.getAttendance(),
                    record.getGrade(),
                    record.getComment());
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void showTranscriptUpdatedMessage(Student student, Discipline discipline, Transcript transcript) {
        System.out.printf(
                "Транскрипт жаңартылды: студент %s %s, пән: %s, Қорытынды баға: %.2f, GPA: %s, Дәстүрлі баға: %s%n",
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
        System.out.printf("Аттестация %s пәні бойынша %s семестрінде сәтті жабылды.%n",
                discipline.getName(),
                semester
        );
    }

    @Override
    public boolean confirmAttestationClosure() {
        System.out.println("Сіз бұл пән бойынша аттестацияны жабуға сенімдісіз бе? (иә/жоқ):");
        String input = InputValidatorUtil.validateNonEmptyInput("Иә немесе жоқ деп енгізіңіз.");
        return input.equalsIgnoreCase("иә");
    }

}
