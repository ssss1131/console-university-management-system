package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.*;
import java.util.logging.Logger;

public class StudentViewEn implements StudentView {
    private static final Logger logger = Logger.getLogger(StudentView.class.getName());


    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("No available disciplines for registration.");
            return;
        }

        System.out.println("Available disciplines for registration:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Code: %s, Credits: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    @Override
    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Enter the numbers of disciplines to register (comma-separated):");
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
                    System.out.println("No disciplines selected. Please try again.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers separated by commas.");
            }
        }
    }

    @Override
    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("You have successfully registered for the following disciplines:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Code: %s, Credits: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    @Override
    public void displayInfo(Student student) {
        System.out.println("Hello " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Your ID: " + student.getId());
        System.out.println("Study duration: " + student.getStudyDuration());
        System.out.println("School: " + student.getSchool());
        System.out.println("Organization: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());

    }

    @Override
    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Registered Disciplines ===");
        System.out.println("Semester: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("You have not registered for any disciplines this semester.");
            return;
        }

        System.out.printf("%-10s %-30s %-10s %-15s%n", "Code", "Name", "Credits", "Course Type");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
        System.out.println("===============================================================\n");
    }

    @Override
    public void showMessage(String message){
        System.out.println(message);

    }

    @Override
    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Would you like to view disciplines for a specific semester or all semesters?");
        System.out.println("1. Specific Semester");
        System.out.println("2. All Semesters");

        int choice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("No semesters available.");
            return null;
        }

        System.out.println("Select a semester:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Enter your choice: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }

    @Override
    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("No disciplines registered for the selected semester.");
            return;
        }

        System.out.println("\n=== Registered Disciplines for the Selected Semester ===");
        showDisciplineList(disciplines);
        System.out.println("=========================================================\n");
    }

    @Override
    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("No disciplines registered across semesters.");
            return;
        }

        System.out.println("\n=== All Registered Disciplines ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Semester: " + semester);
            showDisciplineList(disciplines);
        }
        System.out.println("==================================\n");
    }

    @Override
    public void showDiploma(DiplomaProject project, GraduateStudent student) {
        while (true) {
            System.out.println("\n=== Diploma Project Details ===");
            System.out.printf("Title: %s%n", project.getTitle());
            System.out.printf("Description: %s%n", project.getDescription());
            System.out.printf("Supervisor: %s%n",
                    project.getSupervisor() != null ? project.getSupervisor().getFirstName() : "Not assigned");
            System.out.println("Published Papers: ");
            if (project.getPublishedPapers().isEmpty()) {
                System.out.println("  No published papers.");
            } else {
                for (int i = 0; i < project.getPublishedPapers().size(); i++) {
                    ResearchPaper paper = project.getPublishedPapers().get(i);
                    System.out.printf("  %d. %s (DOI: %s)%n", i + 1, paper.getTitle(), paper.getDoi());
                }
            }

            System.out.println("\nActions:");
            System.out.println("1. Edit Title");
            System.out.println("2. Edit Description");
            System.out.println("3. Add Research Paper");
            System.out.println("4. Exit and save changes");

            int choice = InputValidatorUtil.validateIntegerInput("Select an action: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new title:");
                    String newTitle = InputValidatorUtil.validateNonEmptyInput("Title cannot be empty.");
                    project.setTitle(newTitle);
                    System.out.println("Title updated successfully.");
                }
                case 2 -> {
                    System.out.println("Enter new description:");
                    String newDescription = InputValidatorUtil.validateNonEmptyInput("Description cannot be empty.");
                    project.setDescription(newDescription);
                    System.out.println("Description updated successfully.");
                }
                case 3 -> {
                    System.out.println("Select research papers to add:");
                    List<ResearchPaper> availablePapers = ResearcherService.getInstance().getResearchPapers(student)
                            .stream().filter(researchPaper -> !project.getPublishedPapers().contains(researchPaper))
                            .toList();
                    Vector<ResearchPaper> selectedPapers = selectResearchPapers(availablePapers);
                    project.getPublishedPapers().addAll(selectedPapers);
                    System.out.println("Selected papers added to the diploma project.");
                }
                case 4 -> {
                    System.out.println("Exiting diploma project view.");
                    return;
                }
            }
        }
    }

    @Override
    public String getOrganizationName() {
        System.out.println("Enter organization name:");
        return InputValidatorUtil.validateNonEmptyInput("Enter non empty name");
    }

    @Override
    public void showAlreadyExistingOrganizationName() {
        System.out.println("This name already exists. Please enter unique name");
    }

    @Override
    public String getOrganizationDescription() {
        System.out.println("Enter organization description: ");
        return InputValidatorUtil.validateNonEmptyInput("Enter non empty description");
    }

    @Override
    public Organization selectOrganization(List<Organization> organizations) {
        if (organizations == null || organizations.isEmpty()) {
            System.out.println("No organizations available.");
            return null;
        }

        System.out.println("\n=== Available Organizations ===");
        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, organization.getName(), organization.getDescription());
            if (organization.getMembers().isEmpty()) {
                System.out.println("   Members: No members yet.");
            } else {
                System.out.println("   Members:");
                organization.getMembers().forEach((student, role) ->
                        System.out.printf("      - %s %s (%s)%n", student.getFirstName(), student.getLastName(), role));
            }
        }
        System.out.println("Select an organization by number or press 0 to cancel: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Enter valid number ", 0, organizations.size());

        if (choice == 0) {
            System.out.println("Selection canceled.");
            return null;
        }

        return organizations.get(choice - 1);
    }

    @Override
    public OrganizationRole selectOrganizationRole(List<OrganizationRole> availableRoles) {
        if (availableRoles.isEmpty()) {
            System.out.println("Not available roles");
            return null;
        }

        System.out.println("\n=== available roles ===");
        for (int i = 0; i < availableRoles.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableRoles.get(i));
        }
        System.out.println("========================\n");
        System.out.println("Select role number: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Enter valid number ", 1, availableRoles.size());

        return availableRoles.get(choice - 1);
    }

    @Override
    public void showOrganizationInfo(List<Organization> organizations) {
        if (organizations.isEmpty()) {
            System.out.println("You are not a member of any organization.");
            return;
        }

        System.out.println("\n=== Organization Information ===");

        for (Organization organization : organizations) {
            System.out.printf("Name: %s%n", organization.getName());
            System.out.printf("Description: %s%n", organization.getDescription());
            System.out.println("Members:");
            if (organization.getMembers().isEmpty()) {
                System.out.println("  No members in this organization.");
            } else {
                organization.getMembers().forEach((student, role) -> {
                    System.out.printf("  - %s %s (Role: %s)%n",
                            student.getFirstName(), student.getLastName(), role.name());
                });
            }
            System.out.println("=".repeat(50));
        }
    }

    private Vector<ResearchPaper> selectResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("No research papers available.");
            return new Vector<>();
        }

        System.out.println("Available Research Papers:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
        }

        System.out.println("Enter paper numbers (comma-separated) or press Enter to skip:");
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
                        System.out.println("Invalid paper number: " + (paperIndex + 1));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Skipping paper selection.");
            }
        }

        return selectedPapers;
    }



    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Code", "Name", "Credits", "Course Type");
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
        System.out.println("No semester selected.");
    }

    @Override
    public void showDiscipline(Discipline discipline) {
        System.out.printf("Discipline: %s (Code: %s, Credits: %d, Semester: %s, Course Type: %s)%n",
                discipline.getName(), discipline.getCode(), discipline.getCredits(),
                discipline.getSemester(), discipline.getCourseType());
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("No disciplines available.");
    }

    @Override
    public void showMarksHeader() {
        System.out.printf("%-10s %-10s %-15s %-10s %-20s%n", "Date", "Lesson", "Attendance", "Grade", "Comment");
    }

    @Override
    public void showMarkRow(LessonRecord record) {
        System.out.printf("%-10s %-10s %-15s %-10.2f %-20s%n",
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());
    }

    @Override
    public void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence) {
        System.out.println("------------------------------------------------");
        System.out.printf("Total Marks: %.2f%n", totalMarks);
        System.out.printf("Total Presence: %d%n", totalPresence);
        System.out.printf("Total Absence: %d%n", totalAbsence);
        System.out.println("================================================");
    }

    @Override
    public void showNoMarksMessage(Discipline discipline) {
        System.out.println("No marks for Discipline: " + discipline.getName());
    }

    @Override
    public void showSemesterHeader(Semester semester) {
        System.out.printf("\n=== Semester: %s ===\n", semester.toString());
    }
}
