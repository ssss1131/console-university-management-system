package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.*;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectEnum;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectMultipleEnums;
import static main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil.validateIntegerInput;

public class ManagerViewKz implements ManagerView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<String, Object> getPostInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Посттың тақырыбын енгізіңіз:");
        data.put(TITLE_ATTRIBUTE, scanner.nextLine());

        System.out.println("Посттың мазмұнын енгізіңіз:");
        data.put(CONTENT_ATTRIBUTE, scanner.nextLine());

        User loggedUser = Menu.getInstance().getLoggedUser();
        if (loggedUser != null) {
            data.put(AUTHOR_ATTRIBUTE, loggedUser);
        } else {
            System.out.println("Кіру жасалған пайдаланушы табылмады. Авторды орнату мүмкін емес.");
            return null;
        }

        data.put(DATE_ATTRIBUTE, LocalDate.now());
        return data;
    }

    @Override
    public void displayPostAdded(Post post) {
        System.out.println("Пост сәтті қосылды:");
        System.out.println(post);
    }

    @Override
    public void displayDisciplinesByStatus(List<Discipline> assignedDisciplines, List<Discipline> cancelledDisciplines) {
        System.out.println("\n=== Белгіленген пәндер ===");
        if (assignedDisciplines.isEmpty()) {
            System.out.println("ASSIGNED мәртебесімен пәндер жоқ.");
        } else {
            for (int i = 0; i < assignedDisciplines.size(); i++) {
                Discipline discipline = assignedDisciplines.get(i);
                System.out.printf("%d. %s (Код: %s, Мектеп: %s, Семестр: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }

        System.out.println("\n=== Күші жойылған пәндер ===");
        if (cancelledDisciplines.isEmpty()) {
            System.out.println("CANCELLED мәртебесімен пәндер жоқ.\n");
        } else {
            for (int i = 0; i < cancelledDisciplines.size(); i++) {
                Discipline discipline = cancelledDisciplines.get(i);
                System.out.printf("%d. %s (Код: %s, Мектеп: %s, Семестр: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }
    }

    @Override
    public String getCode() {
        System.out.println("Пәннің кодын енгізіңіз:");
        return InputValidatorUtil.validateNonEmptyInput("Пәннің коды бос болмауы керек.");
    }

    @Override
    public void showAlreadyExistingMessage() {
        System.out.println("Бұл пәннің коды бұрыннан қолданыста. Бірегей код енгізіңіз.");
    }

    @Override
    public Discipline getNewDisciplineInput(String code, List<Discipline> disciplines) {

        System.out.println("Пәннің атауын енгізіңіз:");
        String name = InputValidatorUtil.validateNonEmptyInput("Пәннің атауы бос болмауы керек.");

        System.out.println("Мектепті таңдаңыз:");
        School school = selectEnum(School.class);

        System.out.println("Пәннің кредиттерін енгізіңіз:");
        int credits = validateIntegerInput("Кредиттер оң бүтін сан болуы керек.", 1, 6);

        System.out.println("Семестр жылын енгізіңіз:");
        int yearStart = validateIntegerInput("Жыл жарамды оң сан болуы керек.", 2001, 2100);

        System.out.println("Семестр кезеңін таңдаңыз:");
        Period period = selectEnum(Period.class);

        Semester semester = new Semester(yearStart, period);

        System.out.println("Нысаналы аудиторияны таңдаңыз:");
        StudentRole targetAudience = selectEnum(StudentRole.class);

        System.out.println("Нысаналы мамандықтарды таңдаңыз:");
        Set<Program> targetSpecializations = new HashSet<>();

        switch (targetAudience) {
            case BACHELOR -> {
                System.out.println("Бакалавр мамандықтарын таңдаңыз:");
                targetSpecializations.addAll(selectMultipleEnums(Specialization.class));
            }
            case MASTER -> {
                System.out.println("Магистратура бағдарламаларын таңдаңыз:");
                targetSpecializations.addAll(selectMultipleEnums(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("PhD бағдарламаларын таңдаңыз:");
                targetSpecializations.addAll(selectMultipleEnums(PhdProgram.class));
            }
        }

        List<Discipline> schoolDisciplines = disciplines.stream()
                .filter(discipline -> discipline.getSchool().equals(school))
                .filter(discipline -> discipline.getSemester().compareTo(semester) < 0)
                .filter(discipline -> discipline.getTargetAudience() == targetAudience)
                .toList();
        Set<String> prerequisites = new HashSet<>();
        if (!schoolDisciplines.isEmpty()) {
            System.out.println("Алғышарттарды таңдаңыз (нөмір бойынша) немесе бос қалдырыңыз (мысалы, 1,3,5):");
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
                            System.out.println("Қате таңдау. Бұл жазба өткізіп жіберіледі.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Қате енгізу. Бұл жазба өткізіп жіберіледі.");
                    }
                }
            }
        } else {
            System.out.println("Бұл мектеп үшін пәндер табылмады, алғышарттар бос болады.");
        }

        System.out.println("Курс түрін таңдаңыз:");
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
        System.out.println("Жаңа пәнді жасау сұранысы деканға сәтті жіберілді.");
    }

    @Override
    public List<Discipline> selectDisciplinesToFinalize(List<Discipline> approvedDisciplines) {
        System.out.println("Дерекқорға қосу үшін пәндерді таңдаңыз (нөмірлерді үтірмен бөліп енгізіңіз) немесе бас тартыңыз:\n");
        for (int i = 0; i < approvedDisciplines.size(); i++) {
            Discipline discipline = approvedDisciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Мектеп: %s, Семестр: %s)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
        }

        System.out.println("\nДерекқорға қосқыңыз келетін пәндердің нөмірлерін енгізіңіз (мысалы, 1, 3, 5) немесе бас тартыңыз:");
        while (true) {
            try {
                String input = new Scanner(System.in).nextLine();
                if (input.equalsIgnoreCase(CANCEL_INPUT)) {
                    System.out.println("Шығу");
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
                System.out.println("Қате енгізу. Дұрыс нөмірлерді үтірмен бөліп енгізіңіз.");
            }
        }
    }

    @Override
    public Semester getSemesterInput() {
        System.out.println("Жылды енгізіңіз (мысалы, 2023): ");
        int year = validateIntegerInput("Жыл жарамды оң сан болуы керек.", 2001, 2100);
        System.out.println("Семестр кезеңін таңдаңыз (КӨКТЕМ/КҮЗ): ");
        Period period = EnumSelectionUtil.selectEnum(Period.class);
        return new Semester(year, period);
    }

    @Override
    public StudentRole getStudentRoleInput() {
        System.out.println("Студент рөлін таңдаңыз:");
        return EnumSelectionUtil.selectEnum(StudentRole.class);
    }

    @Override
    public int getCourseInput() {
        System.out.println("Курстың нөмірін енгізіңіз (мысалы, 1): ");
        return validateIntegerInput("Курс нөмірі оң бүтін сан болуы керек.", 1, 7);
    }

    @Override
    public List<Discipline> selectDisciplinesForCourse(List<Discipline> availableDisciplines) {
        if (availableDisciplines.isEmpty()) {
            System.out.println("Бұл курсқа тағайындау үшін пәндер жоқ.");
            return new ArrayList<>();
        }

        System.out.println("Қолжетімді пәндер:");
        for (int i = 0; i < availableDisciplines.size(); i++) {
            Discipline discipline = availableDisciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Кредиттер: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }

        while (true) {
            try {
                System.out.println("Курсқа тағайындау үшін пәндердің нөмірлерін енгізіңіз (үтірмен бөлінген):");
                String[] input = scanner.nextLine().split(",");

                List<Discipline> selectedDisciplines = Arrays.stream(input)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(index -> index >= 1 && index <= availableDisciplines.size())
                        .mapToObj(index -> availableDisciplines.get(index - 1))
                        .collect(Collectors.toList());

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("Пәндер таңдалмады. Кем дегенде бір пәнді таңдаңыз.");
                    continue;
                }

                return selectedDisciplines;
            } catch (NumberFormatException e) {
                System.out.println("Қате енгізу. Нөмірлерді үтірмен бөліп енгізіңіз.");
            }
        }
    }

    @Override
    public Program selectProgram(StudentRole role) {
        System.out.println("Тіркеу ашылатын бағдарламаларды таңдаңыз: ");
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
        throw new IllegalArgumentException("Мұндай рөл жоқ");
    }

    @Override
    public void showRegistrationInfo(Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> registrationMap, Semester semester) {
        if (semester != null) {
            showSemesterInfo(semester);
        }

        if (registrationMap.isEmpty()) {
            System.out.println("Тіркеу туралы ақпарат жоқ.");
            return;
        }

        System.out.println("=== Тіркеу туралы ақпарат ===");
        for (Map.Entry<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> courseEntry : registrationMap.entrySet()) {
            int course = courseEntry.getKey();
            System.out.println("\nКурс: " + course);

            Map<StudentRole, Map<Program, List<Discipline>>> roleMap = courseEntry.getValue();
            for (Map.Entry<StudentRole, Map<Program, List<Discipline>>> roleEntry : roleMap.entrySet()) {
                StudentRole role = roleEntry.getKey();
                System.out.println("  Рөл: " + role);

                Map<Program, List<Discipline>> programMap = roleEntry.getValue();
                for (Map.Entry<Program, List<Discipline>> programEntry : programMap.entrySet()) {
                    Program program = programEntry.getKey();
                    System.out.println("    Бағдарлама: " + program);

                    List<Discipline> disciplines = programEntry.getValue();
                    if (disciplines.isEmpty()) {
                        System.out.println("      Тіркелген пәндер жоқ.");
                    } else {
                        System.out.println("      Пәндер:");
                        for (Discipline discipline : disciplines) {
                            System.out.printf("        - Код: %s, Атауы: %s, Кредиттер: %d, Семестр: %s%n",
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
        System.out.println(semester + " үшін тіркеу ашық.");
    }

    @Override
    public void showSuccessClosingRegistration(Semester semester) {
        if (semester == null) {
            System.out.println("Тіркеу бұрыннан жабылған!");
        } else {
            System.out.println(semester + " үшін тіркеу сәтті жабылды!");
        }
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("Тағайындауға пәндер жоқ.");
    }

    @Override
    public void showNoTeachersAvailableMessage() {
        System.out.println("Тағайындауға мұғалімдер жоқ.");
    }

    @Override
    public void showDisciplineAssignedMessage(Discipline discipline, Teacher teacher) {
        System.out.printf("Пән '%s' мұғалімге %s %s сәтті тағайындалды.%n",
                discipline.getName(), teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public Discipline selectDiscipline(List<Discipline> disciplines) {
        if (disciplines.isEmpty()) {
            System.out.println("Таңдау үшін пәндер жоқ.");
            return null;
        }

        System.out.println("Тізімнен пәнді таңдаңыз:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Мектеп: %s, Семестр: %s)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
        }

        int choice = validateIntegerInput("Пәннің нөмірін енгізіңіз:", 1, disciplines.size());
        return disciplines.get(choice - 1);
    }

    @Override
    public Teacher selectTeacher(List<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("Таңдау үшін мұғалімдер жоқ.");
            return null;
        }

        System.out.println("Тізімнен мұғалімді таңдаңыз:");
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            System.out.printf("%d. %s %s (Мектеп: %s, Деңгей: %s, Рейтинг: %s)%n",
                    i + 1, teacher.getFirstName(), teacher.getLastName(),
                    teacher.getSchool(), teacher.getTeachingDegree(), teacher.getRating());
        }

        int choice = validateIntegerInput("Мұғалімнің нөмірін енгізіңіз:", 1, teachers.size());
        return teachers.get(choice - 1);
    }

    @Override
    public GraduateStudent showFreeStudents(List<GraduateStudent> students) {
        if (students.isEmpty()) {
            System.out.println("Ешқандай бос студенттер жоқ.");
            return null;
        }

        System.out.println("\n=== Бос студенттер ===");
        System.out.printf("%-5s %-15s %-15s %-20s %-10s%n",
                "№", "Аты", "Тегі", "Мектебі", "Кредиттер");
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
        System.out.println("Студентті нөмірі бойынша таңдаңыз немесе бас тарту үшін 0 басыңыз: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Нормальный сан жазаныз", 0, students.size());

        if (choice == 0) {
            return null;
        }

        return students.get(choice - 1);
    }

    @Override
    public ResearchSupervisor showSupervisors(List<ResearchSupervisor> supervisors) {
        if (supervisors.isEmpty()) {
            System.out.println("Қолжетімді ғылыми жетекшілер жоқ.");
            return null;
        }

        System.out.println("\n=== Қолжетімді ғылыми жетекшілер ===");
        System.out.printf("%-5s %-15s %-15s%n", "№", "Аты", "Тегі");
        System.out.println("-".repeat(60));

        int index = 1;
        for (ResearchSupervisor supervisor : supervisors) {
            System.out.printf("%-5d %-15s %-15s%n",
                    index++,
                    supervisor.getFirstName(),
                    supervisor.getLastName());
        }

        System.out.println("-".repeat(60));

        System.out.println("Ғылыми жетекшіні нөмірі бойынша таңдаңыз немесе бас тарту үшін 0 басыңыз: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Нормальный сан жазаныз", 0, supervisors.size());

        if (choice == 0) {
            return null;
        }

        return supervisors.get(choice - 1);
    }
}
