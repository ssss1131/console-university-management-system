package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

public class Teacher implements CanViewCourses, CanViewStudents {
    private Rating rating;
    private School school;
    private TeachingDegree teachingDegree;

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

    public void sendComplaints() {
        //TODO
    }

    public int putMarks() {
        //TODO
        return 0;
    }

    public void viewMarks() {
        //TODO
    }
}
