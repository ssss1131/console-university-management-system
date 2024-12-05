package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.UUID;
import java.util.Vector;

public abstract class BaseUser implements User {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Vector<Post> notifications;

    public BaseUser() {}

    public BaseUser(UUID id, String email, String firstName, String lastName, Vector<Post> notifications) {
        this.id = id;
        this.email = email;
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
}
