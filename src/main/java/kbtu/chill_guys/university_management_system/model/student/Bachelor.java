package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The {@code Bachelor} class represents a bachelor-level student in the university management system.
 * It extends the {@code Student} class and includes additional details specific to bachelor students,
 * such as their specialization.
 */
public class Bachelor extends Student {

    /**
     * The specialization of the bachelor student.
     */
    private Specialization specialization;

    /**
     * Constructs a {@code Bachelor} object with detailed attributes, including organization and specialization.
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
     * @param gpa             the numeric GPA of the student
     * @param credits         the number of credits earned
     * @param studyDuration   the duration of the study program, in years
     * @param organization    the organization the student is part of
     * @param specialization  the specialization of the student
     */
    public Bachelor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    School school, LocalDate enrollmentDate, double gpa, int credits,
                    int studyDuration, Organization organization, Specialization specialization) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits,
                studyDuration, organization);
        this.specialization = specialization;
    }

    /**
     * Constructs a {@code Bachelor} object without organization details but includes specialization.
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
     * @param specialization  the specialization of the student
     */
    public Bachelor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    School school, LocalDate enrollmentDate, Integer credits,
                    Integer studyDuration, Specialization specialization) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration);
        this.specialization = specialization;
    }

    /**
     * Gets the program of the bachelor student.
     *
     * @return the program, represented by the student's specialization
     */
    @Override
    public Program getProgram() {
        return specialization;
    }

    /**
     * Gets the role of the student as a bachelor.
     *
     * @return the role of the student, which is {@code StudentRole.BACHELOR}
     */
    @Override
    public StudentRole getStudentRole() {
        return StudentRole.BACHELOR;
    }

    /**
     * Gets the specialization of the bachelor student.
     *
     * @return the specialization
     */
    public Specialization getSpecialization() {
        return this.specialization;
    }

    /**
     * Sets the specialization of the bachelor student.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Provides a string representation of the bachelor student.
     *
     * @return a string containing details about the student and their specialization
     */
    @Override
    public String toString() {
        return "Bachelor{" +
                "specialization=" + specialization +
                "} " + super.toString();
    }
}
