package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * The {@code User} class serves as an abstract base class representing a user
 * in the university management system. It encapsulates common user-related
 * attributes such as ID, role, email, password, salt, and personal details.
 * This class implements the {@code Subscriber} interface and provides
 * basic functionality for handling research paper updates.
 */
public abstract class User implements Serializable, Subscriber {

    /**
     * Unique identifier for the user.
     */
    private UUID id;

    /**
     * Role of the user in the system, represented by the {@code UserRole} enumeration.
     */
    private UserRole role;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Encrypted password of the user.
     */
    private String password;

    /**
     * Salt used for password encryption.
     */
    private String salt;

    /**
     * First name of the user.
     */
    private String firstName;

    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Default constructor for the {@code User} class.
     */
    public User() {}

    /**
     * Constructs a {@code User} with specified attributes.
     *
     * @param id        the unique identifier of the user
     * @param role      the role of the user
     * @param email     the email address of the user
     * @param password  the encrypted password of the user
     * @param salt      the salt used for password encryption
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     */
    public User(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's ID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the new ID for the user
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the user's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the new first name for the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the new last name for the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the role of the user.
     *
     * @return the user's role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the new role for the user
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets the encrypted password of the user.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the salt used for password encryption.
     *
     * @return the user's salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Updates the user with a new research paper notification.
     *
     * @param post the research paper to notify the user about
     */
    @Override
    public void update(ResearchPaper post) {
        // Implementation for updating the user with research paper details.
    }

    /**
     * Provides a string representation of the user.
     *
     * @return a string containing user details
     */
    @Override
    public String toString() {
        return "User [id: " + id + ", role: " + role + ", email: " + email + ", firstName: " + firstName + ", lastName: " + lastName + "]" + password;
    }

    /**
     * Checks if this user is equal to another object.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    /**
     * Computes the hash code for this user.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
