package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.*;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectEnum;
import static main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil.*;

public class AdminViewEn implements AdminView {
    @Override
    public Map<String, Object> getUserInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Enter user role:");
        UserRole role = selectEnum(UserRole.class);
        data.put(USER_ROLE_ATTRIBUTE, role);

        System.out.println("Enter password:");
        data.put(PASSWORD_ATTRIBUTE, validateNonEmptyInput("Password cannot be empty"));

        System.out.println("Enter first name:");
        data.put(FIRSTNAME_ATTRIBUTE, validateNonEmptyInput("First name cannot be empty"));

        System.out.println("Enter last name:");
        data.put(LASTNAME_ATTRIBUTE, validateNonEmptyInput("Last name cannot be empty"));

        switch (role) {
            case BACHELOR, MASTER, PHD -> handleStudentInput(data);
            case TEACHER -> handleTeacherInput(data);
            case MANAGER -> handleManagerInput(data);
            case DEAN -> handleDeanInput(data);
            case PROFESSOR -> handleProfessorInput(data);
            case RESEARCH_SUPERVISOR -> handleResearchSupervisorInput(data);
        }

        return data;
    }

    private void handleResearchSupervisorInput(Map<String, Object> data) {
        System.out.println("Enter salary:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Salary must be a positive integer", 0, Integer.MAX_VALUE));
    }

    private void handleProfessorInput(Map<String, Object> data) {
        System.out.println("Enter salary:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Salary must be a positive integer", 0, Integer.MAX_VALUE));

        System.out.println("Enter rating:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Rating must be a positive integer and in range 0 - 100", 0, 100));

        System.out.println("Enter school:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));
    }

    @Override
    public void displayUserCreated(User user) {
        System.out.println("User created successfully!");
        System.out.println(user);
    }

    private void handleStudentInput(Map<String, Object> data) {
        System.out.println("Enter school:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Enter enrollment date (yyyy-MM-dd):");
        data.put(ENROLLMENT_DATE_ATTRIBUTE, validateDateInput("Invalid date format. Please enter a valid date."));

        data.put(CREDITS_ATTRIBUTE, 0);

        System.out.println("Enter study duration (years):");
        data.put(STUDY_DURATION_ATTRIBUTE, validateIntegerInput("Study duration must be a positive integer", 0, Integer.MAX_VALUE));
        UserRole role = (UserRole) data.get(USER_ROLE_ATTRIBUTE);
        switch (role) {
            case MASTER -> {
                System.out.println("Choose a Master Program:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("Choose a PhD Program:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(PhdProgram.class));
            }
            case BACHELOR -> {
                System.out.println("Choose a Bachelor Program:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(Specialization.class));
            }
        }
    }

    private void handleTeacherInput(Map<String, Object> data) {
        System.out.println("Enter salary:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Salary must be a positive integer", 0, Integer.MAX_VALUE));

        System.out.println("Enter rating:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Rating must be a positive integer", 0, 100));

        System.out.println("Enter school:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Enter teaching degree:");
        data.put(TEACHING_DEGREE_ATTRIBUTE, selectEnum(TeachingDegree.class));
    }

    private void handleManagerInput(Map<String, Object> data) {
        System.out.println("Enter salary:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Salary must be a positive integer", 0, Integer.MAX_VALUE));

        System.out.println("Enter manager type:");
        data.put(MANAGER_TYPE_ATTRIBUTE, selectEnum(ManagerType.class));
    }

    private void handleDeanInput(Map<String, Object> data) {
        System.out.println("Enter salary:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Salary must be a positive integer", 0, Integer.MAX_VALUE));
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public UUID getUserIdForDeletion() {
        System.out.println("Enter the ID of the user to delete:");
        return InputValidatorUtil.validateUUIDInput("Invalid UUID format. Please try again.");
    }

    @Override
    public LogPeriod getLogPeriod() {
        System.out.println("Enter log period:");
        return selectEnum(LogPeriod.class);
    }

    @Override
    public void displayLogs(List<String> logs) {
        if (logs.isEmpty()) {
            System.out.println("No logs found for the selected period.");
        } else {
            System.out.println("Logs for the selected period:");
            logs.forEach(System.out::println);
        }
    }

    @Override
    public void displayUserAlreadyExists() {
        System.out.println("The mail must be unique. Try again!");
    }


    @Override
    public void displayAllUsers(List<User> users) {
        System.out.println("=== Users ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. %s %s (Email: %s, Role: %s)%n",
                    i + 1, users.get(i).getFirstName(), users.get(i).getLastName(), users.get(i).getEmail(), users.get(i).getRole());
        }
    }

    @Override
    public int getUserIndexForDeletion(int maxIndex) {
        return InputValidatorUtil.validateIntegerInput(
                "Enter the number of the user you want to delete:",
                1,
                maxIndex
        ) - 1;
    }

    @Override
    public boolean confirmDeletion(User user) {
        System.out.printf("Are you sure you want to delete user %s %s (Email: %s)? (yes/no): ",
                user.getFirstName(), user.getLastName(), user.getEmail());
        String input = InputValidatorUtil.validateNonEmptyInput("Please enter 'yes' or 'no'.");
        return input.equalsIgnoreCase("yes");
    }

    @Override
    public void displayNoUsersToDelete() {
        System.out.println("No users available to delete!");
    }

    @Override
    public void displayUserDeletionCancelled() {
        System.out.println("User deletion cancelled.");
    }

    @Override
    public void displayUserDeletedSuccessfully() {
        System.out.println("User deleted successfully.");
    }

    @Override
    public Map<String, Object> getFieldsForUpdate(User user) {
        System.out.println("Select fields to update for user: ");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("Enter the numbers separated by commas (e.g., 1,3):");

        String input = InputValidatorUtil.validateNonEmptyInput("Invalid input. Please try again.");
        String[] fields = input.split(",");

        Map<String, Object> updatedFields = new HashMap<>();
        for (String field : fields) {
            switch (field.trim()) {
                case "1" -> {
                    System.out.println("Enter new first name:");
                    updatedFields.put("firstName", validateNonEmptyInput("First name cannot be empty."));
                }
                case "2" -> {
                    System.out.println("Enter new last name:");
                    updatedFields.put("lastName", validateNonEmptyInput("Last name cannot be empty."));
                }
                case "3" -> {
                    System.out.println("Enter new email:");
                    updatedFields.put("email", validateEmailInput("Invalid email format. Please try again."));
                }
                default -> System.out.println("Invalid field number: " + field.trim());
            }
        }
        return updatedFields;
    }

    @Override
    public void displayNoUsersForUpdate() {
        System.out.println("No users available to update!");
    }

    @Override
    public void displayUserUpdatedSuccessfully() {
        System.out.println("User updated successfully.");
    }
}

