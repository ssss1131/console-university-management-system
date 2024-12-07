package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.UUID;
import java.util.Vector;

public class Teacher extends Employee implements CanViewCourses, CanViewStudents {
    private Rating rating;
    private School school;
    private TeachingDegree teachingDegree;

    public Teacher(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   Vector<Post> notifications, int salary, Rating rating, School school, TeachingDegree teachingDegree) {
        super(id, role, email, password, salt, firstName, lastName, notifications, salary);
        this.rating = rating;
        this.school = school;
        this.teachingDegree = teachingDegree;
    }

    public Teacher() {

    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public TeachingDegree getTeachingDegree() {
        return this.teachingDegree;
    }

    public void setTeachingDegree(TeachingDegree teachingDegree) {
        this.teachingDegree = teachingDegree;
    }
}
