package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewMarks;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class Student extends User implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {
    private School school;
    private LocalDate enrollmentDate;
    private Gpa gpa;
    private Integer credits;
    private Integer studyDuration;
    private Organization organization;

    public Student() {
        super();
    }

    public Student(UUID id, UserRole role, String email, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, GPA school1, int credits, int studyDuration, Organization organization) {
        super(id, role, email, firstName, lastName, notifications);
        this.school = school;
        this.enrollmentDate = enrollmentDate;
        this.credits = credits;
        this.studyDuration = studyDuration;
        this.organization = organization;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Gpa getGpa() {
        return this.gpa;
    }

    public void setGpa(Gpa gpa) {
        this.gpa = gpa;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getStudyDuration() {
        return this.studyDuration;
    }

    public void setStudyDuration(Integer studyDuration) {
        this.studyDuration = studyDuration;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public void setOrganization(Organization organization) {
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
    }

    public Transcript viewTranscript() {
        //TODO
        return null;
    }

    public Vector<Mark> viewMarks() {
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

    public Map<Course, Integer> viewAttendance() {
        //TODO
        return null;
    }

    public String viewAcademicStanding() {
        //TODO
        return "";
    }
}
