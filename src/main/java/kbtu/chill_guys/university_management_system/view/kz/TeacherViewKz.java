package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
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
        System.out.println("Сабақтың атауын енгізіңіз:");
        String lesson = InputValidatorUtil.validateNonEmptyInput("Сабақтың атауы бос болмауы керек. Қайталап көріңіз.");

        String dateInput = InputValidatorUtil.validateDateInput("Күннің пішімі дұрыс емес. yyyy-MM-dd форматында енгізіңіз.");
        LocalDate date = LocalDate.parse(dateInput);

        System.out.println("Қатысу мәртебесін таңдаңыз:");
        Attendance attendance = EnumSelectionUtil.selectEnum(Attendance.class);

        System.out.println("Бағаны енгізіңіз (0-100):");
        double grade = InputValidatorUtil.validateIntegerInput("Баға 0-ден 100-ге дейін болуы керек.", 0, 100);

        System.out.println("Пікірді енгізіңіз:");
        String comment = InputValidatorUtil.validateNonEmptyInput("Пікір бос болмауы керек. Қайталап көріңіз.");

        return new LessonRecord(date, lesson, attendance, grade, comment);
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
}
