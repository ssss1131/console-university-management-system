package main.java.kbtu.chill_guys.university_management_system.view.ru;

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
        String lesson = InputValidatorUtil.validateNonEmptyInput("Название урока не может быть пустым. Попробуйте снова.");

        String dateInput = InputValidatorUtil.validateDateInput("Неверный формат даты. Используйте yyyy-MM-dd.");
        LocalDate date = LocalDate.parse(dateInput);

        System.out.println("Выберите статус посещаемости:");
        Attendance attendance = EnumSelectionUtil.selectEnum(Attendance.class);

        System.out.println("Введите оценку (0-100):");
        double grade = InputValidatorUtil.validateIntegerInput("Оценка должна быть в диапазоне от 0 до 100.", 0, 100);

        System.out.println("Введите комментарий:");
        String comment = InputValidatorUtil.validateNonEmptyInput("Комментарий не может быть пустым. Попробуйте снова.");

        return new LessonRecord(date, lesson, attendance, grade, comment);
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
}
