package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The {@code PHD} class represents a PhD student in the university management system.
 * It extends the {@link GraduateStudent} class and includes functionality specific to
 * PhD-level programs. PhD students can engage in research activities and manage
 * their research projects and papers.
 */
public class PHD extends GraduateStudent {

    /**
     * The specific PhD program the student is enrolled in.
     */
    private PhdProgram phdProgram;

    /**
     * Constructs a new {@code PHD} student with all attributes except organization.
     *
     * @param id             the unique identifier of the student
     * @param role           the role of the user in the system
     * @param email          the email address of the student
     * @param password       the password of the student
     * @param salt           the salt used for password encryption
     * @param firstName      the first name of the student
     * @param lastName       the last name of the student
     * @param school         the school the student belongs to
     * @param enrollmentDate the enrollment date of the student
     * @param credits        the number of credits earned
     * @param studyDuration  the duration of the study program in years
     * @param phdProgram     the PhD program the student is enrolled in
     */
    public PHD(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, PhdProgram phdProgram) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration);
        this.phdProgram = phdProgram;
    }

    /**
     * Constructs a new {@code PHD} student with all attributes including organization.
     *
     * @param id             the unique identifier of the student
     * @param role           the role of the user in the system
     * @param email          the email address of the student
     * @param password       the password of the student
     * @param salt           the salt used for password encryption
     * @param firstName      the first name of the student
     * @param lastName       the last name of the student
     * @param school         the school the student belongs to
     * @param enrollmentDate the enrollment date of the student
     * @param credits        the number of credits earned
     * @param studyDuration  the duration of the study program in years
     * @param organization   the organization the student is part of
     * @param phdProgram     the PhD program the student is enrolled in
     */
    public PHD(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, Organization organization, PhdProgram phdProgram) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration, organization);
        this.phdProgram = phdProgram;
    }

    /**
     * Gets the PhD program of the student.
     *
     * @return the PhD program
     */
    public PhdProgram getPhdProgram() {
        return this.phdProgram;
    }

    /**
     * Sets the PhD program for the student.
     *
     * @param phdProgram the new PhD program
     */
    public void setPhdProgram(PhdProgram phdProgram) {
        this.phdProgram = phdProgram;
    }

    /**
     * Gets the program of the student, which corresponds to their PhD program.
     *
     * @return the program of the student
     */
    @Override
    public Program getProgram() {
        return phdProgram;
    }

    /**
     * Gets the role of the student, which is {@code StudentRole.PHD}.
     *
     * @return the role of the student
     */
    @Override
    public StudentRole getStudentRole() {
        return StudentRole.PHD;
    }

    /**
     * Adds a research paper to the student's list of research papers.
     *
     * @param researchPaper the research paper to add
     */
    @Override
    public void addResearchPaper(ResearchPaper researchPaper) {
        getResearchPapers().add(researchPaper);
    }

    /**
     * Adds a research project to the student's list of research projects.
     *
     * @param researchProject the research project to add
     */
    @Override
    public void addResearchProjects(ResearchProject researchProject) {
    }

    /**
     * Provides a string representation of the {@code PHD} student.
     *
     * @return a string containing details about the student
     */
    @Override
    public String toString() {
        return "PHD{" +
                "phdProgram=" + phdProgram +
                "} " + super.toString();
    }
}
