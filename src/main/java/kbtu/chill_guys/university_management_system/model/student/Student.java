package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewMarks;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import main.java.kbtu.chill_guys.university_management_system.util.NumericToEnumMapper;

import java.time.LocalDate;
import java.util.*;

public abstract class Student extends User implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {
    private School school;
    private LocalDate enrollmentDate;
    private double gpaNumeric;
    private Gpa gpa;
    private Integer credits;
    private Integer studyDuration;
    private Organization organization;

    public Student() {
        super();
    }

    public Student(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits,
                   Integer studyDuration, Organization organization) {
        super(id, role, email, password, salt, firstName, lastName, notifications);
        this.school = school;
        this.enrollmentDate = enrollmentDate;
        this.credits = credits;
        this.studyDuration = studyDuration;
        this.organization = organization;
    }

    public Student(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration) {
        super(id, role, email, password, salt, firstName, lastName, notifications);
        this.school = school;
        this.enrollmentDate = enrollmentDate;
        this.credits = credits;
        this.studyDuration = studyDuration;
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
    public double getGpaNumeric() {
        return gpaNumeric;
    }

    public void setGpaNumeric(double gpaNumeric) {
        this.gpaNumeric = gpaNumeric;
        this.gpa = NumericToEnumMapper.mapGpa(gpaNumeric);
    }

    public abstract Program getProgram();

    public abstract StudentRole getStudentRole();



    @Override
    public String toString() {
        return "Student{" +
               "school=" + school +
               ", enrollmentDate=" + enrollmentDate +
               ", gpaNumeric=" + gpaNumeric +
               ", gpa=" + gpa +
               ", credits=" + credits +
               ", studyDuration=" + studyDuration +
               ", organization=" + organization +
               "} " + super.toString();
    }

}
