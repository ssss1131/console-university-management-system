package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;

import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

/**
 * The {@code Manager} class represents a manager in the university management system.
 * It extends the {@link Employee} class and implements the {@link CanViewRequests} and
 * {@link CanViewTeachers} interfaces, enabling the manager to view requests and teachers.
 */
public class Manager extends Employee implements CanViewRequests, CanViewTeachers {

    /**
     * The type of manager, represented by the {@link ManagerType} enumeration.
     */
    private ManagerType managerType;

    /**
     * Default constructor for the {@code Manager} class.
     */
    public Manager() {
    }

    /**
     * Constructs a new {@code Manager} with specified attributes.
     *
     * @param id           the unique identifier of the manager
     * @param role         the role of the manager in the system
     * @param email        the email address of the manager
     * @param password     the password of the manager
     * @param salt         the salt used for password encryption
     * @param firstName    the first name of the manager
     * @param lastName     the last name of the manager
     * @param salary       the salary of the manager
     * @param managerType  the type of the manager (e.g., department, organization)
     */
    public Manager(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   int salary, ManagerType managerType) {
        super(id, role, email, password, salt, firstName, lastName, salary);
        this.managerType = managerType;
    }

    /**
     * Gets the type of the manager.
     *
     * @return the manager type
     */
    public ManagerType getManagerType() {
        return this.managerType;
    }

    /**
     * Sets the type of the manager.
     *
     * @param managerType the new manager type
     */
    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    /**
     * Provides a string representation of the {@code Manager} object.
     *
     * @return a string containing the manager's details
     */
    @Override
    public String toString() {
        return "Manager{" +
                "managerType=" + managerType +
                "} " + super.toString();
    }
}
