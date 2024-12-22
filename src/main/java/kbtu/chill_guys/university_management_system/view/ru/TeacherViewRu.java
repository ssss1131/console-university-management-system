package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;

import java.time.LocalDate;
import java.util.List;

public class TeacherViewRu implements TeacherView {
    @Override
    public Semester selectSemester() {
        System.out.println("Выберите семестр:");
        int year = InputValidatorUtil.validateIntegerInput("Введите корректный год:", 2000, 2100);
        Period period = EnumSelectionUtil.selectEnum(Period.class);
        return new Semester(year, period);
    }

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("Семестр не выбран. Возврат в главное меню.");
    }

    @Override
    public Discipline selectDiscipline(List<Discipline> disciplines) {
        System.out.println("Выберите дисциплину:");
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, disciplines.get(i).getName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Некорректный выбор. Попробуйте снова.", 1, disciplines.size());
        return disciplines.get(choice - 1);
    }

    @Override
    public Student selectStudent(List<Student> students) {
        System.out.println("Выберите студента:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s %s%n", i + 1, students.get(i).getFirstName(), students.get(i).getLastName());
        }
        int choice = InputValidatorUtil.validateIntegerInput("Некорректный выбор. Попробуйте снова.", 1, students.size());
        return students.get(choice - 1);
    }

    @Override
    public LessonRecord createLessonRecord() {
        System.out.println("Введите название урока:");
        String lesson = InputValidatorUtil.validateNonEmptyInput("Название урока не может быть пустым. Пожалуйста, попробуйте снова.");

        String dateInput = InputValidatorUtil.validateDateInput("Неверный формат даты. Используйте yyyy-MM-dd.");
        LocalDate date = LocalDate.parse(dateInput);

        System.out.println("Выберите посещаемость:");
        Attendance attendance = EnumSelectionUtil.selectEnum(Attendance.class);

        Double grade = null;
        if (attendance != Attendance.ABSENT) {
            System.out.println("Введите оценку (0-100):");
            grade = (double) InputValidatorUtil.validateIntegerInput("Оценка должна быть в пределах от 0 до 100.", 0, 100);
        }

        System.out.println("Введите комментарий (опционально, нажмите Enter, чтобы пропустить):");
        String comment = InputValidatorUtil.validateOptionalInput();

        return new LessonRecord(date, lesson, attendance, grade != null ? grade : 0.0, comment);
    }


    @Override
    public void showNoDisciplinesMessage(Semester semester) {
        System.out.println("Нет дисциплин для семестра: " + semester);
    }

    @Override
    public void showNoStudentsMessage(Discipline discipline) {
        System.out.println("Нет студентов для дисциплины: " + discipline.getName());
    }

    @Override
    public void showRecordAddedMessage(LessonRecord record) {
        System.out.println("Запись успешно добавлена: " + record);
    }

    @Override
    public void showStudentRecords(Student student, List<LessonRecord> lessonRecords) {
        System.out.printf("=== Записи студента: %s %s ===%n", student.getFirstName(), student.getLastName());
        if (lessonRecords.isEmpty()) {
            System.out.println("Записей нет.");
            return;
        }

        System.out.println("Дата       | Занятие        | Посещаемость | Оценка | Комментарии");
        System.out.println("---------------------------------------------------------------");
        for (LessonRecord record : lessonRecords) {
            System.out.printf("%s | %-14s | %-12s | %-6.2f | %s%n",
                    record.getDate(),
                    record.getLesson(),
                    record.getAttendance(),
                    record.getGrade(),
                    record.getComment());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Override
    public void showTranscriptUpdatedMessage(Student student, Discipline discipline, Transcript transcript) {
        System.out.printf(
                "Транскрипт обновлен для студента %s %s по дисциплине %s: Итоговая оценка: %.2f, GPA: %s, Традиционная оценка: %s%n",
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
        System.out.printf("Аттестация по дисциплине %s в семестре %s успешно закрыта.%n",
                discipline.getName(),
                semester
        );
    }

    @Override
    public boolean confirmAttestationClosure() {
        System.out.println("Вы уверены, что хотите закрыть аттестацию по этой дисциплине? (да/нет):");
        String input = InputValidatorUtil.validateNonEmptyInput("Введите 'да' или 'нет'.");
        return input.equalsIgnoreCase("да");
    }

    @Override
    public void showTeacherRating(Teacher teacher) {
        System.out.printf("Ваш рейтинг: %s (%d баллов)%n", teacher.getRating(), teacher.getRating().getScore());
    }

    @Override
    public String getComment() {
        System.out.println("Введите ваш комментарий:");
        return InputValidatorUtil.validateNonEmptyInput("Комментарий не может быть пустым. Пожалуйста, попробуйте снова.");
    }

    @Override
    public UrgencyLevel selectUrgencyLevel() {
        System.out.println("Выберите уровень срочности:");
        return EnumSelectionUtil.selectEnum(UrgencyLevel.class);
    }

    @Override
    public void showComplaintCreatedMessage(Discipline discipline, Student student, UrgencyLevel urgencyLevel) {
        System.out.printf("Жалоба на %s %s по предмету %s с уровнем срочности %s успешно создана.%n",
                student.getFirstName(), student.getLastName(), discipline.getName(), urgencyLevel);
    }
}
