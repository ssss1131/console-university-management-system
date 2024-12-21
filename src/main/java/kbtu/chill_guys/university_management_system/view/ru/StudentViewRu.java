package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
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

    @Override
    public void showDiploma(DiplomaProject project, GraduateStudent student) {
        while (true) {
            System.out.println("\n=== Детали дипломного проекта ===");
            System.out.printf("Тема: %s%n", project.getTitle());
            System.out.printf("Описание: %s%n", project.getDescription());
            System.out.printf("Научный руководитель: %s%n",
                    project.getSupervisor() != null ? project.getSupervisor().getFirstName() : "Не назначен");
            System.out.println("Опубликованные статьи:");
            if (project.getPublishedPapers().isEmpty()) {
                System.out.println("  Опубликованных статей нет.");
            } else {
                for (int i = 0; i < project.getPublishedPapers().size(); i++) {
                    ResearchPaper paper = project.getPublishedPapers().get(i);
                    System.out.printf("  %d. %s (DOI: %s)%n", i + 1, paper.getTitle(), paper.getDoi());
                }
            }

            System.out.println("\nДействия:");
            System.out.println("1. Изменить тему");
            System.out.println("2. Изменить описание");
            System.out.println("3. Добавить статьи");
            System.out.println("4. Выйти");

            int choice = InputValidatorUtil.validateIntegerInput("Выберите действие: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    System.out.println("Введите новую тему:");
                    String newTitle = InputValidatorUtil.validateNonEmptyInput("Тема не может быть пустой.");
                    project.setTitle(newTitle);
                    System.out.println("Тема успешно обновлена.");
                }
                case 2 -> {
                    System.out.println("Введите новое описание:");
                    String newDescription = InputValidatorUtil.validateNonEmptyInput("Описание не может быть пустым.");
                    project.setDescription(newDescription);
                    System.out.println("Описание успешно обновлено.");
                }
                case 3 -> {
                    System.out.println("Выберите статьи для добавления:");
                    List<ResearchPaper> availablePapers = ResearcherService.getInstance().getResearchPapers(student);
                    Vector<ResearchPaper> selectedPapers = selectResearchPapers(availablePapers);
                    project.getPublishedPapers().addAll(selectedPapers);
                    System.out.println("Статьи успешно добавлены в дипломный проект.");
                }
                case 4 -> {
                    System.out.println("Выход из просмотра дипломного проекта.");
                    UserRepository userRepository = new UserRepository();
                    userRepository.update(student);
                    return;
                }
            }
        }
    }

    private Vector<ResearchPaper> selectResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("Нет доступных статей.");
            return new Vector<>();
        }

        System.out.println("Доступные статьи:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
        }

        System.out.println("Введите номера статей (через запятую) или нажмите Enter для пропуска:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        Vector<ResearchPaper> selectedPapers = new Vector<>();
        if (!input.isEmpty()) {
            try {
                String[] indices = input.split(",");
                for (String index : indices) {
                    int paperIndex = Integer.parseInt(index.trim()) - 1;
                    if (paperIndex >= 0 && paperIndex < papers.size()) {
                        selectedPapers.add(papers.get(paperIndex));
                    } else {
                        System.out.println("Некорректный номер статьи: " + (paperIndex + 1));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пропуск выбора статей.");
            }
        }

        return selectedPapers;
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
}
