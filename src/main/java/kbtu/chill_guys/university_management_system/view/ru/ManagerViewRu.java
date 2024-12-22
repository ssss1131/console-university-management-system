package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.*;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectEnum;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectMultipleEnums;
import static main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil.validateIntegerInput;

public class ManagerViewRu implements ManagerView {
    private final Scanner scanner = new Scanner(System.in);
    private final DisciplineService disciplineService = new DisciplineService();

    public void addNewCourses() {

    }


    public void displayCoursesForRegistration() {
        //TODO
    }

    @Override
    public Map<String, Object> getPostInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Введите название поста:");
        data.put(TITLE_ATTRIBUTE, scanner.nextLine());

        System.out.println("Введите содержимое поста:");
        data.put(CONTENT_ATTRIBUTE, scanner.nextLine());

        User loggedUser = Menu.getInstance().getLoggedUser();
        if (loggedUser != null) {
            data.put(AUTHOR_ATTRIBUTE, loggedUser);
        } else {
            System.out.println("Нет вошедшего в систему пользователя. Невозможно указать автора.");
            return null;
        }

        data.put(DATE_ATTRIBUTE, LocalDate.now());
        return data;
    }

    @Override
    public void displayPostAdded(Post post) {
        System.out.println("Пост успешно добавлен:");
        System.out.println(post);
    }

    @Override
    public void displayDisciplinesByStatus(List<Discipline> assignedDisciplines, List<Discipline> cancelledDisciplines) {
        System.out.println("\n=== Назначенные дисциплины ===");
        if (assignedDisciplines.isEmpty()) {
            System.out.println("Нет дисциплин со статусом 'Назначено'.");
        } else {
            for (int i = 0; i < assignedDisciplines.size(); i++) {
                Discipline discipline = assignedDisciplines.get(i);
                System.out.printf("%d. %s (Код: %s, Школа: %s, Семестр: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }

        System.out.println("\n=== Отмененные дисциплины ===");
        if (cancelledDisciplines.isEmpty()) {
            System.out.println("Нет дисциплин со статусом 'Отменено'.\n");
        } else {
            for (int i = 0; i < cancelledDisciplines.size(); i++) {
                Discipline discipline = cancelledDisciplines.get(i);
                System.out.printf("%d. %s (Код: %s, Школа: %s, Семестр: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }
    }


    public Discipline getNewDisciplineInput() {
        System.out.println("Введите код дисциплины:");
        String code;
        while (true) {
            code = InputValidatorUtil.validateNonEmptyInput("Код дисциплины не может быть пустым.");
            if (!disciplineService.isUniqueCode(code)) {
                System.out.println("Этот код дисциплины уже используется. Пожалуйста, введите уникальный код.");
            } else {
                break;
            }
        }

        System.out.println("Введите название дисциплины:");
        String name = InputValidatorUtil.validateNonEmptyInput("Название дисциплины не может быть пустым.");

        System.out.println("Выберите школу:");
        School school = selectEnum(School.class);

        System.out.println("Введите количество кредитов дисциплины:");
        int credits = validateIntegerInput("Кредиты должны быть положительным числом.", 1, 6);

        System.out.println("Введите год семестра:");
        int yearStart = validateIntegerInput("Год должен быть положительным числом.", 2001, 2100);

        System.out.println("Выберите период семестра:");
        Period period = selectEnum(Period.class);

        Semester semester = new Semester(yearStart, period);

        System.out.println("Выберите целевую аудиторию: ");
        StudentRole targetAudience = selectEnum(StudentRole.class);

        System.out.println("Выберите целевые специализации: ");
        Set<Program> targetSpecializations = new HashSet<>();

        switch (targetAudience) {
            case BACHELOR -> {
                System.out.println("Выберите специализации бакалавриата:");
                targetSpecializations.addAll(selectMultipleEnums(Specialization.class));
            }
            case MASTER -> {
                System.out.println("Выберите магистерские программы:");
                targetSpecializations.addAll(selectMultipleEnums(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("Выберите программы PhD:");
                targetSpecializations.addAll(selectMultipleEnums(PhdProgram.class));
            }
        }

        List<Discipline> schoolDisciplines = disciplineService.getDisciplinesBySchool(school).stream()
                .filter(discipline -> discipline.getSemester().compareTo(semester) < 0)
                .filter(discipline -> discipline.getTargetAudience() == targetAudience)
                .toList();

        Set<String> prerequisites = new HashSet<>();
        if (!schoolDisciplines.isEmpty()) {
            System.out.println("Выберите предварительные дисциплины (по номерам) или оставьте пустым (например, 1,3,5):");
            for (int i = 0; i < schoolDisciplines.size(); i++) {
                Discipline discipline = schoolDisciplines.get(i);
                System.out.printf("%d. %s, %s, %s%n",
                        i + 1,
                        discipline.getCode(),
                        discipline.getName(),
                        discipline.getSemester());
            }

            prerequisites = new HashSet<>();
            String prerequisitesInput = scanner.nextLine();
            if (!prerequisitesInput.isBlank()) {
                String[] selectedIndices = prerequisitesInput.split(",");
                for (String index : selectedIndices) {
                    try {
                        int idx = Integer.parseInt(index.trim()) - 1;
                        if (idx >= 0 && idx < schoolDisciplines.size()) {
                            prerequisites.add(schoolDisciplines.get(idx).getCode());
                        } else {
                            System.out.println("Неверный выбор. Пропускаем эту запись.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод. Пропускаем эту запись.");
                    }
                }
            }
        } else {
            System.out.println("Для этой школы не найдено дисциплин, предварительные дисциплины будут пустыми.");
        }

        System.out.println("Выберите тип курса:");
        CourseType courseType = selectEnum(CourseType.class);

        return new Discipline.Builder()
                .code(code)
                .name(name)
                .school(school)
                .credits(credits)
                .semester(semester)
                .targetAudience(targetAudience)
                .targetSpecializations(targetSpecializations)
                .prerequisites(prerequisites)
                .courseType(courseType)
                .build();
    }

    @Override
    public void showRequestSentConfirmation() {
        System.out.println("Запрос на создание новой дисциплины успешно отправлен декану.");
    }

    @Override
    public List<Discipline> selectDisciplinesToFinalize(List<Discipline> approvedDisciplines) {
        System.out.println("Выберите дисциплины для финализации (введите номера через запятую) или отмените:\n");
        for (int i = 0; i < approvedDisciplines.size(); i++) {
            Discipline discipline = approvedDisciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Школа: %s, Семестр: %s)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
        }

        System.out.println("\nВведите номера дисциплин, которые вы хотите добавить в базу данных (например, 1, 3, 5) или отмените:");
        while (true) {
            try {
                String input = new Scanner(System.in).nextLine();
                if (input.equalsIgnoreCase(CANCEL_INPUT)) {
                    System.out.println("Выход");
                    return List.of();
                }
                String[] parts = input.split(",");
                List<Integer> indices = Arrays.stream(parts)
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .filter(i -> i >= 1 && i <= approvedDisciplines.size())
                        .toList();

                List<Discipline> selectedDisciplines = new ArrayList<>();
                for (int index : indices) {
                    selectedDisciplines.add(approvedDisciplines.get(index - 1));
                }
                return selectedDisciplines;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите корректные номера через запятую.");
            }
        }
    }

    @Override
    public Semester getSemesterInput() {
        System.out.println("Введите год (например, 2023): ");
        int year = validateIntegerInput("Год должен быть положительным числом.", 2001, 2100);
        System.out.println("Выберите период семестра (ВЕСНА/ОСЕНЬ): ");
        Period period = EnumSelectionUtil.selectEnum(Period.class);
        return new Semester(year, period);
    }

    @Override
    public StudentRole getStudentRoleInput() {
        System.out.println("Выберите роль студента:");
        return EnumSelectionUtil.selectEnum(StudentRole.class);
    }

    @Override
    public int getCourseInput() {
        System.out.println("Введите номер курса (например, 1): ");
        return validateIntegerInput("Номер курса должен быть положительным числом.", 1, 7);
    }

    @Override
    public List<Discipline> selectDisciplinesForCourse(List<Discipline> availableDisciplines) {
        if (availableDisciplines.isEmpty()) {
            System.out.println("Нет доступных дисциплин для назначения на курс.");
            return new ArrayList<>();
        }

        System.out.println("Доступные дисциплины:");
        for (int i = 0; i < availableDisciplines.size(); i++) {
            Discipline discipline = availableDisciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Кредиты: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }

        while (true) {
            try {
                System.out.println("Введите номера дисциплин для назначения на курс (через запятую):");
                String[] input = scanner.nextLine().split(",");

                List<Discipline> selectedDisciplines = Arrays.stream(input)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(index -> index >= 1 && index <= availableDisciplines.size())
                        .mapToObj(index -> availableDisciplines.get(index - 1))
                        .collect(Collectors.toList());

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("Дисциплины не выбраны. Пожалуйста, выберите хотя бы одну дисциплину.");
                    continue;
                }

                return selectedDisciplines;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите номера через запятую.");
            }
        }
    }

    @Override
    public Program selectProgram(StudentRole role) {
        System.out.println("Выберите программы, для которых будет открыта регистрация: ");
        switch (role) {
            case BACHELOR -> {
                return selectEnum(Specialization.class);
            }
            case MASTER -> {
                return selectEnum(MasterProgram.class);
            }
            case PHD -> {
                return selectEnum(PhdProgram.class);
            }
        }
        throw new IllegalArgumentException("Несуществующая роль");
    }

    @Override
    public void showRegistrationInfo(Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> registrationMap, Semester semester) {
        if (semester != null) {
            showSemesterInfo(semester);
        }

        if (registrationMap.isEmpty()) {
            System.out.println("Информация о регистрации отсутствует.");
            return;
        }

        System.out.println("=== Информация о регистрации ===");
        for (Map.Entry<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> courseEntry : registrationMap.entrySet()) {
            int course = courseEntry.getKey();
            System.out.println("\nКурс: " + course);

            Map<StudentRole, Map<Program, List<Discipline>>> roleMap = courseEntry.getValue();
            for (Map.Entry<StudentRole, Map<Program, List<Discipline>>> roleEntry : roleMap.entrySet()) {
                StudentRole role = roleEntry.getKey();
                System.out.println("Роль: " + role);

                Map<Program, List<Discipline>> programMap = roleEntry.getValue();
                for (Map.Entry<Program, List<Discipline>> programEntry : programMap.entrySet()) {
                    Program program = programEntry.getKey();
                    System.out.println("    Программа: " + program);

                    List<Discipline> disciplines = programEntry.getValue();
                    if (disciplines.isEmpty()) {
                        System.out.println("      Нет зарегистрированных дисциплин.");
                    } else {
                        System.out.println("      Дисциплины:");
                        for (Discipline discipline : disciplines) {
                            System.out.printf("        - Код: %s, Название: %s, Кредиты: %d, Семестр: %s%n",
                                    discipline.getCode(), discipline.getName(), discipline.getCredits(), discipline.getSemester());
                        }
                    }
                }
            }
        }
        System.out.println("================================");
    }

    @Override
    public void showSemesterInfo(Semester semester) {
        System.out.println("Регистрация открыта для " + semester);
    }

    @Override
    public void showSuccessClosingRegistration(Semester semester) {
        if (semester == null) {
            System.out.println("Регистрация уже закрыта!");
        } else {
            System.out.println("Регистрация для " + semester + " успешно закрыта!");
        }
    }

    @Override
    public GraduateStudent showFreeStudents(List<GraduateStudent> students) {
        if (students.isEmpty()) {
            System.out.println("Нет доступных студентов.");
            return null;
        }

        System.out.println("\n=== Свободные студенты ===");
        System.out.printf("%-5s %-15s %-15s %-20s %-10s%n",
                "№", "Имя", "Фамилия", "Школа", "Кредиты");
        System.out.println("-".repeat(90));

        int index = 1;
        for (GraduateStudent student : students) {
            System.out.printf("%-5d %-15s %-15s %-20s %-10d %n",
                    index++,
                    student.getFirstName(),
                    student.getLastName(),
                    student.getSchool().name(),
                    student.getCredits() != null ? student.getCredits() : 0);
        }

        System.out.println("-".repeat(90));
        System.out.println("Выберите студента по номеру или нажмите 0 для отмены: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Выберите нормальное число", 0, students.size());

        if (choice == 0) {
            return null;
        }

        return students.get(choice - 1);
    }

    @Override
    public ResearchSupervisor showSupervisors(List<ResearchSupervisor> supervisors) {
        if (supervisors.isEmpty()) {
            System.out.println("Нет доступных научных руководителей.");
            return null;
        }

        System.out.println("\n=== Доступные научные руководители ===");
        System.out.printf("%-5s %-15s %-15s%n", "№", "Имя", "Фамилия");
        System.out.println("-".repeat(60));

        int index = 1;
        for (ResearchSupervisor supervisor : supervisors) {
            System.out.printf("%-5d %-15s %-15s%n",
                    index++,
                    supervisor.getFirstName(),
                    supervisor.getLastName());
        }

        System.out.println("-".repeat(60));
        System.out.println("Выберите научного руководителя по номеру или нажмите 0 для отмены: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Выберите нормальное число", 0, supervisors.size());

        if (choice == 0) {
            return null;
        }

        return supervisors.get(choice - 1);
    }


}
