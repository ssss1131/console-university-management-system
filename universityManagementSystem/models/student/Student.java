package universityManagementSystem.models.student;

import universityManagementSystem.models.BaseUser;
import universityManagementSystem.permissions.CanBeResearcher;
import universityManagementSystem.permissions.CanViewCourses;
import universityManagementSystem.permissions.CanViewMarks;
import universityManagementSystem.permissions.CanViewTeachers;

import java.time.LocalDate;

public class Student extends BaseUser implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {
    private School school;
    private LocalDate enrollmentDate;
    private Gpa gpa;
    private Integer credits;
    private Integer studyDuration;
    private Organization organization;
    

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
    private universityManagementSystem.Organization setOrganization(Organization organization) {
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
