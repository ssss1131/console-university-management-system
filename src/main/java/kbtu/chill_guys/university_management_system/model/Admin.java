package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;

import java.util.UUID;

public class Admin extends User {
    public Admin(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName) {
        super(id, role, email, password, salt, firstName, lastName);
    }
}
