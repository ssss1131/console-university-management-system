package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.UUID;

/**
 * The {@code Dean} class represents a dean in the university management system.
 * It extends the {@link Employee} class and implements the {@link CanViewRequests} and
 * {@link CanViewStudents} interfaces, enabling the dean to view requests and student information.
 */
public class Dean extends Employee implements CanViewRequests, CanViewStudents {

    /**
     * Constructs a new {@code Dean} with specified attributes.
     *
     * @param id           the unique identifier of the dean
     * @param role         the role of the dean in the system
     * @param email        the email address of the dean
     * @param password     the password of the dean
     * @param salt         the salt used for password encryption
     * @param firstName    the first name of the dean
     * @param lastName     the last name of the dean
     * @param salary       the salary of the dean
     */
    public Dean(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                int salary) {
        super(id, role, email, password, salt, firstName, lastName, salary);
    }
}
