package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewMarks;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import universityManagementSystem.models.BaseUser;

import java.time.LocalDate;
import java.util.Map;

public class Student extends BaseUser implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {

    private School school;
    private LocalDate enrollmentDate;
    private Gpa gpa;
    private Integer credits;
    private Integer studyDuration;
    private universityManagementSystem.models.student.Organization organization;


    private School getSchool() {
        return this.school;
    }

    private School setSchool(School school) {
        this.school = school;
    }

    private LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    private LocalDate setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    private Gpa getGpa() {
        return this.gpa;
    }

    private Gpa setGpa(Gpa gpa) {
        this.gpa = gpa;
    }

    private Integer getCredits() {
        return this.credits;
    }

    private Integer setCredits(Integer credits) {
        this.credits = credits;
    }

    private Integer getStudyDuration() {
        return this.studyDuration;
    }

    private Integer setStudyDuration(Integer studyDuration) {
        this.studyDuration = studyDuration;
    }

    private universityManagementSystem.Organization getOrganization() {
        return this.organization;
    }

    private universityManagementSystem.Organization setOrganization(universityManagementSystem.models.student.Organization organization) {
        this.organization = organization;
    }

    public boolean registerForCourse() {
        //TODO
        return false;
    }

    public boolean dropCourse() {
        //TODO
        return false;
    }

    public void rateTeacher() {
        //TODO
        return null;
    }

    public universityManagementSystem.models.academic.Transcript viewTranscript() {
        //TODO
        return null;
    }

    public Vector<universityManagementSystem.models.academic.Mark> viewMarks() {
        //TODO
        return null;
    }

    public boolean joinOrganization() {
        //TODO
        return false;
    }

    public boolean leaveOrganization() {
        //TODO
        return false;
    }

    public Map<universityManagementSystem.models.academic.Course, Integer> viewAttendance() {
        //TODO
        return null;
    }

    public String viewAcademicStanding() {
        //TODO
        return "";
    }


}
