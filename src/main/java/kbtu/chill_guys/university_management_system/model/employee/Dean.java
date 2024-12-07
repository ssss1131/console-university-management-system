package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.UUID;
import java.util.Vector;

public class Dean extends Employee implements CanViewRequests, CanViewStudents {
    public Dean() {}

    public Dean(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                Vector<Post> notifications, int salary) {
        super(id, role, email, password, salt, firstName, lastName, notifications, salary);
    }
}
