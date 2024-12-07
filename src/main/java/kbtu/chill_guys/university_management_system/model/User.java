package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

public abstract class User implements Serializable {
    private UUID id;
    private UserRole role;
    private String email;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;
    private Vector<Post> notifications;

    public User() {}

    public User(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notifications = notifications;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Vector<Post> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(Vector<Post> notifications) {
        this.notifications = notifications;
    }

    public  UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User [id: " + id + ", role: " + role + ", email: " + email + ", firstName: " + firstName + ", lastName: " + lastName + "]" + password;
    }

    public String getSalt() {
        return salt;
    }
}
