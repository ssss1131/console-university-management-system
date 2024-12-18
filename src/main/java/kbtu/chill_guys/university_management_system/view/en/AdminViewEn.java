package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
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

        System.out.println("Enter email:");
        data.put(EMAIL_ATTRIBUTE, validateEmailInput("Invalid email format. Please try again."));

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
        }

        return data;
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

        System.out.printf("Enter organization (or %s to skip): ", CANCEL_INPUT);
        String input = validateNonEmptyInput("Invalid organization input.");
        if (!input.equalsIgnoreCase(CANCEL_INPUT)) {
            data.put(ORGANIZATION_ATTRIBUTE, getOrganizationInput());
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

    private Organization getOrganizationInput() {
        System.out.println("Enter organization name:");
        String name = validateNonEmptyInput("Organization name cannot be empty");

        System.out.println("Enter organization description:");
        String description = validateNonEmptyInput("Organization description cannot be empty");

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);

        return organization;
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
}

