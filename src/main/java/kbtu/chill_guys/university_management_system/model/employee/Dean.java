package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.UUID;

public class Dean extends Employee implements CanViewRequests, CanViewStudents {

    public Dean(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                int salary) {
        super(id, role, email, password, salt, firstName, lastName, salary);
    }
}
