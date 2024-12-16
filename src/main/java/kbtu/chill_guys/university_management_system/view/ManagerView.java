package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.*;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.CANCEL_INPUT;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectEnum;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectMultipleEnums;
import static main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil.validateIntegerInput;

public class ManagerView {
    private final Scanner scanner = new Scanner(System.in);
    private final DisciplineService disciplineService = new DisciplineService();

    public void addNewCourses() {

    }


    public void displayCoursesForRegistration() {
        //TODO
    }

    public Post getPostInput() {
        Post post = new Post();

        System.out.println("Enter post title:");
        post.setTitle(scanner.nextLine());

        System.out.println("Enter post content:");
        post.setContent(scanner.nextLine());

        User loggedUser = Menu.getInstance().getLoggedUser();
        if (loggedUser != null) {
            post.setAuthor(loggedUser);
        } else {
            System.out.println("No logged-in user found. Unable to set author.");
            return null;
        }

        post.setDate(LocalDate.now());
        return post;
    }

    public void displayPostAdded(Post post) {
        System.out.println("Post added successfully:");
        System.out.println(post);
    }

    public void displayDisciplinesByStatus(List<Discipline> assignedDisciplines, List<Discipline> cancelledDisciplines) {
        System.out.println("\n=== Assigned Disciplines ===");
        if (assignedDisciplines.isEmpty()) {
            System.out.println("No disciplines with ASSIGNED status.");
        } else {
            for (int i = 0; i < assignedDisciplines.size(); i++) {
                Discipline discipline = assignedDisciplines.get(i);
                System.out.printf("%d. %s (Code: %s, School: %s, Semester: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }

        System.out.println("\n=== Cancelled Disciplines ===");
        if (cancelledDisciplines.isEmpty()) {
            System.out.println("No disciplines with CANCELLED status.\n");
        } else {
            for (int i = 0; i < cancelledDisciplines.size(); i++) {
                Discipline discipline = cancelledDisciplines.get(i);
                System.out.printf("%d. %s (Code: %s, School: %s, Semester: %s)%n",
                        i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
            }
        }
    }


    public Discipline getNewDisciplineInput() {
        System.out.println("Enter discipline code:");
        String code;
        while (true) {
            code = InputValidatorUtil.validateNonEmptyInput("Discipline code cannot be empty.");
            if (!disciplineService.isUniqueCode(code)) {
                System.out.println("This discipline code is already in use. Please enter a unique code.");
            } else {
                break;
            }
        }

        System.out.println("Enter discipline name:");
        String name = InputValidatorUtil.validateNonEmptyInput("Discipline name cannot be empty.");

        System.out.println("Select school:");
        School school = selectEnum(School.class);

        System.out.println("Enter discipline credits:");
        int credits = validateIntegerInput("Credits must be a positive integer.", 1, 6);

        System.out.println("Enter semester year:");
        int yearStart = validateIntegerInput("Year must be a valid positive number.", 2001, 2100);

        System.out.println("Select semester period:");
        Period period = selectEnum(Period.class);

        Semester semester = new Semester(yearStart, period);

        System.out.println("Select target audience: ");
        StudentRole targetAudience = selectEnum(StudentRole.class);

        System.out.println("Select target specializations: ");
        Set<Program> targetSpecializations = new HashSet<>();

        switch (targetAudience) {
            case BACHELOR -> {
                System.out.println("Choose Bachelor Specializations:");
                targetSpecializations.addAll(selectMultipleEnums(Specialization.class));
            }
            case MASTER -> {
                System.out.println("Choose Master Programs:");
                targetSpecializations.addAll(selectMultipleEnums(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("Choose PhD Programs:");
                targetSpecializations.addAll(selectMultipleEnums(PhdProgram.class));
            }
        }

        List<Discipline> schoolDisciplines = disciplineService.getDisciplinesBySchool(school).stream()
                .filter(discipline -> discipline.getSemester().compareTo(semester) < 0)
                .filter(discipline -> discipline.getTargetAudience() == targetAudience)
                .toList();
        Set<String> prerequisites = new HashSet<>();
        if(!schoolDisciplines.isEmpty()){
            System.out.println("Choose prerequisites (by number) or leave empty(e.g 1,3,5):");
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
                            System.out.println("Invalid selection. Skipping this entry.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Skipping this entry.");
                    }
                }
            }
        } else {
            System.out.println("No disciplines found for this school, prerequisites will be empty");
        }

        System.out.println("Select course type:");
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

    public void showRequestSentConfirmation() {
        System.out.println("Successfully send new discipline creation request to dean.");
    }

    public List<Discipline> selectDisciplinesToFinalize(List<Discipline> approvedDisciplines) {
        System.out.println("Select disciplines to finalize (enter numbers separated by commas) or leave:\n");
        for (int i = 0; i < approvedDisciplines.size(); i++) {
            Discipline discipline = approvedDisciplines.get(i);
            System.out.printf("%d. %s (Code: %s, School: %s, Semester: %s)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getSemester());
        }

        System.out.println("\nEnter the numbers of disciplines you want to add to the database (e.g., 1, 3, 5) or cancel:");
        while (true) {
            try {
                String input = new Scanner(System.in).nextLine();
                if(input.equalsIgnoreCase(CANCEL_INPUT)){
                    System.out.println("exiting");
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
                System.out.println("Invalid input. Please enter valid numbers separated by commas.");
            }
        }
    }




    public Semester getSemesterInput() {
        System.out.println("Enter year (e.g., 2023): ");
        int year = validateIntegerInput("Year must be a valid positive number.", 2001, 2100);
        System.out.println("Select semester period (SPRING/FALL): ");
        Period period = EnumSelectionUtil.selectEnum(Period.class);
        return new Semester(year, period);
    }

    public StudentRole getStudentRoleInput() {
        System.out.println("Select student role:");
        return EnumSelectionUtil.selectEnum(StudentRole.class);
    }

    public int getCourseInput() {
        System.out.println("Enter course number (e.g., 1): ");
        return validateIntegerInput("Course number must be a positive integer.", 1, 7);
    }

    public List<Discipline> selectDisciplinesForCourse(List<Discipline> availableDisciplines) {
        if (availableDisciplines.isEmpty()) {
            System.out.println("No available disciplines to assign to the course.");
            return new ArrayList<>();
        }

        System.out.println("Available disciplines:");
        for (int i = 0; i < availableDisciplines.size(); i++) {
            Discipline discipline = availableDisciplines.get(i);
            System.out.printf("%d. %s (Code: %s, Credits: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }

        while (true) {
            try {
                System.out.println("Enter the numbers of disciplines to assign to the course (comma-separated):");
                String[] input = scanner.nextLine().split(",");

                List<Discipline> selectedDisciplines = Arrays.stream(input)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(index -> index >= 1 && index <= availableDisciplines.size())
                        .mapToObj(index -> availableDisciplines.get(index - 1))
                        .collect(Collectors.toList());

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("No disciplines selected. Please choose at least one discipline.");
                    continue;
                }

                return selectedDisciplines;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers separated by commas.");
            }
        }
    }


    public Program selectProgram(StudentRole role){
        System.out.println("Select programs for whom will open registration: ");
        switch (role){
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
        throw new IllegalArgumentException("not existing role");
    }

    public void showRegistrationInfo(Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> registrationMap, Semester semester) {
        if(semester!=null){
            showSemesterInfo(semester);
        }

        if (registrationMap.isEmpty()) {
            System.out.println("No registration information available.");
            return;
        }

        System.out.println("=== Registration Information ===");
        for (Map.Entry<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> courseEntry : registrationMap.entrySet()) {
            int course = courseEntry.getKey();
            System.out.println("\nCourse: " + course);

            Map<StudentRole, Map<Program, List<Discipline>>> roleMap = courseEntry.getValue();
            for (Map.Entry<StudentRole, Map<Program, List<Discipline>>> roleEntry : roleMap.entrySet()) {
                StudentRole role = roleEntry.getKey();
                System.out.println("  Role: " + role);

                Map<Program, List<Discipline>> programMap = roleEntry.getValue();
                for (Map.Entry<Program, List<Discipline>> programEntry : programMap.entrySet()) {
                    Program program = programEntry.getKey();
                    System.out.println("    Program: " + program);

                    List<Discipline> disciplines = programEntry.getValue();
                    if (disciplines.isEmpty()) {
                        System.out.println("      No disciplines registered.");
                    } else {
                        System.out.println("      Disciplines:");
                        for (Discipline discipline : disciplines) {
                            System.out.printf("        - Code: %s, Name: %s, Credits: %d, Semester: %s%n",
                                    discipline.getCode(), discipline.getName(), discipline.getCredits(), discipline.getSemester());
                        }
                    }
                }
            }
        }
        System.out.println("================================");
    }

    public void showSemesterInfo(Semester semester) {
        System.out.println("Registration is open for " + semester);
    }

    public void showSuccessClosingRegistration(Semester semester) {
        if(semester == null){
            System.out.println("registration already closed!");
        }else {
            System.out.println("Registration for " + semester + " successfully closed!");
        }

    }
}
