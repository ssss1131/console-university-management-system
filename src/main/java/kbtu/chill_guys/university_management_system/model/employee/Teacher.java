package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

import static main.java.kbtu.chill_guys.university_management_system.util.NumericToEnumMapper.mapRating;

public class Teacher extends Employee implements CanViewStudents {
    private Rating rating;
    private School school;
    private TeachingDegree teachingDegree;

    public Teacher(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   int salary, int rating, School school, TeachingDegree teachingDegree) {
        super(id, role, email, password, salt, firstName, lastName, salary);
        this.rating = mapRating(rating);
        this.school = school;
        this.teachingDegree = teachingDegree;
    }

    public Teacher(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, int salary, int rating, School school) {
        super(id, role, email, password, salt, firstName, lastName, salary);
        this.rating = mapRating(rating);
        this.school = school;
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

    @Override
    public String toString() {
        return "Teacher{" +
               "rating=" + rating +
               ", school=" + school +
               ", teachingDegree=" + teachingDegree +
               "} " + super.toString();
    }
}
