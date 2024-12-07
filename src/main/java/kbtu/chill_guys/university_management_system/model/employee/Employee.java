package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;

import java.util.UUID;
import java.util.Vector;

public class Employee extends User implements CanViewCourses {
    private Integer salary;

    public Employee() {
        super();
    }

    public Employee(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    Vector<Post> notifications, int salary) {
        super(id, role, email, password, salt, firstName, lastName, notifications);
        this.salary = salary;
    }

    public Integer getSalary() {
        return this.salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void sendMessage() {
        //TODO
    }
    
    public Vector<Student> viewStudents() {
        //TODO
        return null;
    }
}
