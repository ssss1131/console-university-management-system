package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
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
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        }

        post.setDate(LocalDate.now());
        return post;
    }

    public void displayPostAdded(Post post) {
        System.out.println("Post added successfully:");
        System.out.println(post);
    }

    public Discipline getNewDisciplineInput() {
        System.out.println("Enter discipline code:");
        String code = InputValidatorUtil.validateNonEmptyInput("Discipline code cannot be empty.");

        System.out.println("Enter discipline name:");
        String name = InputValidatorUtil.validateNonEmptyInput("Discipline name cannot be empty.");

        System.out.println("Select school:");
        School school = EnumSelectionUtil.selectEnum(School.class);

        System.out.println("Enter discipline credits:");
        int credits = InputValidatorUtil.validateIntegerInput("Credits must be a positive integer.", 1, 100);

        System.out.println("Enter semester start year:");
        int yearStart = InputValidatorUtil.validateIntegerInput("Year must be a valid positive number.", 1900, 2100);

        System.out.println("Enter semester end year:");
        int yearEnd = InputValidatorUtil.validateIntegerInput("Year must be a valid positive number.", yearStart, 2100);

        System.out.println("Select semester period:");
        Period period = EnumSelectionUtil.selectEnum(Period.class);

        Semester semester = new Semester(yearStart, yearEnd, period);

        System.out.println("Select target audience (e.g., BACHELOR, MASTER, PHD):");
        StudentRole targetAudience = EnumSelectionUtil.selectEnum(StudentRole.class);

        System.out.println("Enter prerequisites (discipline codes), separated by commas (or leave empty):");
        String prerequisitesInput = scanner.nextLine();
        Set<String> prerequisites = new HashSet<>();
        if (!prerequisitesInput.isBlank()) {
            String[] prerequisiteCodes = prerequisitesInput.split(",");
            for (String prerequisite : prerequisiteCodes) {
                prerequisites.add(prerequisite.trim());
            }
        }

        System.out.println("Select course type:");
        CourseType courseType = EnumSelectionUtil.selectEnum(CourseType.class);

        return new Discipline.Builder()
                .code(code)
                .name(name)
                .school(school)
                .credits(credits)
                .semester(semester)
                .targetAudience(targetAudience)
                .prerequisites(prerequisites)
                .courseType(courseType)
                .build();
    }
}
