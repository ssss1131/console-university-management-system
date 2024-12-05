package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.UUID;
import java.util.Vector;

public class BaseUser {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Vector<Post> notifications;

    private UUID getId() {
        return this.id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private Vector<Post> getNotifications() {
        return this.notifications;
    }

    private void setNotifications(Vector<Post> notifications) {
        this.notifications = notifications;
    }
}
