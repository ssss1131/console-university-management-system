package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.*;

public class StudentViewRu implements StudentView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Нет доступных дисциплин для регистрации.");
            return;
        }

        System.out.println("Доступные дисциплины для регистрации:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Кредиты: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    @Override
    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Введите номера дисциплин для регистрации (через запятую):");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                List<Discipline> selectedDisciplines = Arrays.stream(parts)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(i -> i >= 1 && i <= availableDisciplines.size())
                        .mapToObj(i -> availableDisciplines.get(i - 1))
                        .toList();

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("Дисциплины не выбраны. Попробуйте снова.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите номера дисциплин, разделенные запятыми.");
            }
        }
    }

    @Override
    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("Вы успешно зарегистрировались на следующие дисциплины:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Код: %s, Кредиты: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    @Override
    public void displayInfo(Student student) {
        System.out.println("Здравствуйте, " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Ваш ID: " + student.getId());
        System.out.println("Длительность обучения: " + student.getStudyDuration());
        System.out.println("Школа: " + student.getSchool());
        System.out.println("Организация: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());
    }

    @Override
    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Зарегистрированные дисциплины ===");
        System.out.println("Семестр: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("Вы не зарегистрированы на дисциплины в этом семестре.");
            return;
        }

        showDisciplineList(disciplines);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Хотите посмотреть дисциплины за определенный семестр или все семестры?");
        System.out.println("1. Определенный семестр");
        System.out.println("2. Все семестры");

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("Нет доступных семестров.");
            return null;
        }

        System.out.println("Выберите семестр:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }

    @Override
    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Нет зарегистрированных дисциплин для выбранного семестра.");
            return;
        }

        System.out.println("\n=== Зарегистрированные дисциплины для выбранного семестра ===");
        showDisciplineList(disciplines);
    }

    @Override
    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("Нет зарегистрированных дисциплин по семестрам.");
            return;
        }

        System.out.println("\n=== Все зарегистрированные дисциплины ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Семестр: " + semester);
            showDisciplineList(disciplines);
        }
    }

    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Код", "Название", "Кредиты", "Тип курса");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
    }

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("Семестр не выбран.");
    }

    @Override
    public void showDiscipline(Discipline discipline) {
        System.out.printf("Дисциплина: %s (Код: %s, Кредиты: %d, Семестр: %s, Тип курса: %s)%n",
                discipline.getName(), discipline.getCode(), discipline.getCredits(),
                discipline.getSemester(), discipline.getCourseType());
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("Дисциплины отсутствуют.");
    }

    @Override
    public void showMarksHeader() {
        System.out.printf("%-10s %-10s %-15s %-10s %-20s%n", "Дата", "Урок", "Посещаемость", "Оценка", "Комментарий");
    }

    @Override
    public void showMarkRow(LessonRecord record) {
        System.out.printf("%-10s %-10s %-15s %-10.2f %-20s%n",
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());
    }

    @Override
    public void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence) {
        System.out.println("------------------------------------------------");
        System.out.printf("Общая сумма оценок: %.2f%n", totalMarks);
        System.out.printf("Общее количество присутствий: %d%n", totalPresence);
        System.out.printf("Общее количество отсутствий: %d%n", totalAbsence);
        System.out.println("================================================");
    }

    @Override
    public void showNoMarksMessage(Discipline discipline) {
        System.out.println("Оценки по дисциплине: " + discipline.getName() + " отсутствуют!");
    }
}
