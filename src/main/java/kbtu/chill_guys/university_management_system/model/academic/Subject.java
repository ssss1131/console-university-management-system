package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;

import java.util.Vector;

public class Subject {
    private School school;
    private String code;
    private String name;
    private Integer credits;
    private GradeBook gradebook;
    private Vector<Subject> prerequisites;
    private CourseType courseType;

    private School getSchool() {
        return this.school;
    }

    private void setSchool(School school) {
        this.school = school;
    }

    private String getCode() {
        return this.code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    private String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private Integer getCredits() {
        return this.credits;
    }

    private void setCredits(Integer credits) {
        this.credits = credits;
    }

    private GradeBook getGradebook() {
        return this.gradebook;
    }

    private void setGradebook(GradeBook gradebook) {
        this.gradebook = gradebook;
    }

    private Vector<Subject> getPrerequisites() {
        return this.prerequisites;
    }

    private void setPrerequisites(Vector<Subject> prerequisites) {
        this.prerequisites = prerequisites;
    }

    private CourseType getCourseType() {
        return this.courseType;
    }

    private void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public double getMinandMax() {
        //TODO
        return 0.0;
    }

    public void viewGradebook() {
        //TODO
    }

    public void getTranscript() {
        //TODO
    }
}
