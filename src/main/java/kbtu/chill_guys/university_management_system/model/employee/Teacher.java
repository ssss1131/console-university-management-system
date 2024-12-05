package main.java.kbtu.chill_guys.university_management_system.model.employee;

import universityManagementSystem.permissions.CanViewCourses;
import universityManagementSystem.permissions.CanViewStudents;

public class Teacher implements CanViewCourses, CanViewStudents {
    private Rating rating;
    private School school;
    private TeachingDegree teachingDegree;

    private Rating getRating() {
        return this.rating;
    }

    private Rating setRating(Rating rating) {
        this.rating = rating;
    }

    private School getSchool() {
        return this.school;

    }

    private void setSchool(School school) {
        this.school = school;
    }
    private TeachingDegree getTeachingDegree() {
        return this.teachingDegree;
    }
    private TeachingDegree setTeachingDegree(TeachingDegree teachingDegree) {
        this.teachingDegree = teachingDegree;
    }
    

    //                          Operations                                  
    public void sendComplaints() {
        //TODO
        return null;
    }
    public int putMarks() {
        //TODO
        return 0;
    }
    public boolean sendComplaints() {
        //TODO
        return false;
    }
    public boolean putMarks() {
        //TODO
        return false;
    }
    public void viewMarks() {
        //TODO
        return null;
    }
    public void getRating() {
        //TODO
        return null;
    }
    
    
}
