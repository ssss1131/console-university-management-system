package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewCourses;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewMarks;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import main.java.kbtu.chill_guys.university_management_system.util.NumericToEnumMapper;

import java.time.LocalDate;
import java.util.*;

/**
 * The {@code Student} class serves as an abstract base class for representing students
 * in the university management system. It extends the {@code User} class and implements
 * several interfaces to provide functionalities such as viewing courses, marks, and teachers,
 * as well as participating in research activities.
 */
public abstract class Student extends User implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {

    /**
     * The school to which the student belongs.
     */
    private School school;

    /**
     * The date of enrollment for the student.
     */
    private LocalDate enrollmentDate;

    /**
     * The numeric representation of the student's GPA.
     */
    private double gpaNumeric;

    /**
     * The GPA of the student, represented by the {@code Gpa} enumeration.
     */
    private Gpa gpa;

    /**
     * The number of credits the student has earned.
     */
    private Integer credits;

    /**
     * The duration of the student's study program, in years.
     */
    private Integer studyDuration;

    /**
     * The organization or student club the student is part of.
     */
    private Organization organization;

    /**
     * Default constructor for the {@code Student} class.
     */
    public Student() {
        super();
    }

    /**
     * Constructs a {@code Student} object with specified attributes.
     *
     * @param id              the unique identifier of the student
     * @param role            the role of the student
     * @param email           the email address of the student
     * @param password        the encrypted password of the student
     * @param salt            the salt used for password encryption
     * @param firstName       the first name of the student
     * @param lastName        the last name of the student
     * @param school          the school to which the student belongs
     * @param enrollmentDate  the date of enrollment
     * @param credits         the number of credits earned
     * @param studyDuration   the duration of the study program, in years
     * @param organization    the organization the student is part of
     */
    public Student(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   School school, LocalDate enrollmentDate, Integer credits,
                   Integer studyDuration, Organization organization) {
        super(id, role, email, password, salt, firstName, lastName);
        this.school = school;
        this.enrollmentDate = enrollmentDate;
        this.credits = credits;
        this.studyDuration = studyDuration;
        this.organization = organization;
    }

    /**
     * Constructs a {@code Student} object without an organization.
     *
     * @param id              the unique identifier of the student
     * @param role            the role of the student
     * @param email           the email address of the student
     * @param password        the encrypted password of the student
     * @param salt            the salt used for password encryption
     * @param firstName       the first name of the student
     * @param lastName        the last name of the student
     * @param school          the school to which the student belongs
     * @param enrollmentDate  the date of enrollment
     * @param credits         the number of credits earned
     * @param studyDuration   the duration of the study program, in years
     */
    public Student(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration) {
        super(id, role, email, password, salt, firstName, lastName);
        this.school = school;
        this.enrollmentDate = enrollmentDate;
        this.credits = credits;
        this.studyDuration = studyDuration;
    }

    /**
     * Gets the school of the student.
     *
     * @return the school
     */
    public School getSchool() {
        return this.school;
    }

    /**
     * Sets the school of the student.
     *
     * @param school the new school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * Gets the enrollment date of the student.
     *
     * @return the enrollment date
     */
    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    /**
     * Sets the enrollment date of the student.
     *
     * @param enrollmentDate the new enrollment date
     */
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Gets the GPA of the student.
     *
     * @return the GPA
     */
    public Gpa getGpa() {
        return this.gpa;
    }

    /**
     * Sets the GPA of the student.
     *
     * @param gpa the new GPA
     */
    public void setGpa(Gpa gpa) {
        this.gpa = gpa;
    }

    /**
     * Gets the number of credits earned by the student.
     *
     * @return the credits
     */
    public Integer getCredits() {
        return this.credits;
    }

    /**
     * Sets the number of credits earned by the student.
     *
     * @param credits the new credits
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    /**
     * Gets the study duration of the student.
     *
     * @return the study duration
     */
    public Integer getStudyDuration() {
        return this.studyDuration;
    }

    /**
     * Sets the study duration of the student.
     *
     * @param studyDuration the new study duration
     */
    public void setStudyDuration(Integer studyDuration) {
        this.studyDuration = studyDuration;
    }

    /**
     * Gets the organization of the student.
     *
     * @return the organization
     */
    public Organization getOrganization() {
        return this.organization;
    }

    /**
     * Sets the organization of the student.
     *
     * @param organization the new organization
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Gets the numeric GPA of the student.
     *
     * @return the numeric GPA
     */
    public double getGpaNumeric() {
        return gpaNumeric;
    }

    /**
     * Sets the numeric GPA of the student and updates the corresponding {@code Gpa} enumeration.
     *
     * @param gpaNumeric the new numeric GPA
     */
    public void setGpaNumeric(double gpaNumeric) {
        this.gpaNumeric = gpaNumeric;
        this.gpa = NumericToEnumMapper.mapGpa(gpaNumeric);
    }

    /**
     * Abstract method to get the program of the student.
     *
     * @return the program
     */
    public abstract Program getProgram();

    /**
     * Abstract method to get the role of the student.
     *
     * @return the student role
     */
    public abstract StudentRole getStudentRole();

    /**
     * Provides a string representation of the student.
     *
     * @return a string containing student details
     */
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
