package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class User implements Serializable, Subscriber {
    private UUID id;
    private UserRole role;
    private String email;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;

    public User() {}

    public User(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
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
    public void update(ResearchPaper post) {

    }

    @Override
    public String toString() {
        return "User [id: " + id + ", role: " + role + ", email: " + email + ", firstName: " + firstName + ", lastName: " + lastName + "]" + password;
    }
    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
