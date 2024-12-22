package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;

import java.util.UUID;

/**
 * The {@code Admin} class represents an administrative user in the university management system.
 * It extends the {@code User} class and inherits its attributes and behaviors.
 */
public class Admin extends User {

    /**
     * Constructs an {@code Admin} object with the specified attributes.
     *
     * @param id        the unique identifier of the admin
     * @param role      the role of the admin, typically {@code UserRole.ADMIN}
     * @param email     the email address of the admin
     * @param password  the encrypted password of the admin
     * @param salt      the salt used for password encryption
     * @param firstName the first name of the admin
     * @param lastName  the last name of the admin
     */
    public Admin(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName) {
        super(id, role, email, password, salt, firstName, lastName);
    }
}
