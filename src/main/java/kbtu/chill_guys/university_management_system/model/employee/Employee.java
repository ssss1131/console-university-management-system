package main.java.kbtu.chill_guys.university_management_system.model.employee;
import universityManagementSystem.models.BaseUser;
import universityManagementSystem.models.student.Student;
import universityManagementSystem.permissions.CanViewCourses;

import java.util.Vector;

public class Employee extends BaseUser implements CanViewCourses {
    private Integer salary;
    private Teacher teacher;

    private Integer getSalary() {
        return this.salary;
    }
    
    private void setSalary(Integer salary) {
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
