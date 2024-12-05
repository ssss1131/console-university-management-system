package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;

import java.util.UUID;
import java.util.Vector;

public class Employee extends BaseUser implements CanViewCourses {
    private Integer salary;
    private Teacher teacher;

    public Employee() {
        super();
    }

    public Employee(UUID id, String email, String firstName, String lastName, Vector<Post> notifications, int salary, Teacher teacher) {
        super(id, email, firstName, lastName, notifications);
        this.salary = salary;
        this.teacher = teacher;
    }

    public Integer getSalary() {
        return this.salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void sendMessage() {
        //TODO
    }
    
    public Vector<Student> viewStudents() {
        //TODO
        return null;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public Vector<Post> viewNews() {
        return null;
    }

    @Override
    public void subscribeJournal(Journal journal) {

    }

    @Override
    public void unsubscribeJournal(Journal journal) {

    }
}
