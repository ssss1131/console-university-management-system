package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The {@code Master} class represents a master's degree student
 * in the university management system. It extends the {@link GraduateStudent}
 * class and includes attributes and methods specific to master's programs.
 */
public class Master extends GraduateStudent {

    /**
     * The specific master's program the student is enrolled in.
     */
    private MasterProgram masterProgram;

    /**
     * Constructs a new {@code Master} student with all attributes, including organization.
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
     * @param masterProgram  the master's program the student is enrolled in
     */
    public Master(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, Organization organization, MasterProgram masterProgram) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration, organization);
        this.masterProgram = masterProgram;
    }

    /**
     * Constructs a new {@code Master} student without an organization.
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
     * @param masterProgram  the master's program the student is enrolled in
     */
    public Master(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, MasterProgram masterProgram) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration);
        this.masterProgram = masterProgram;
    }

    /**
     * Gets the master's program of the student.
     *
     * @return the master's program
     */
    public MasterProgram getMasterProgram() {
        return this.masterProgram;
    }

    /**
     * Sets the master's program for the student.
     *
     * @param masterProgram the new master's program
     */
    public void setMasterProgram(MasterProgram masterProgram) {
        this.masterProgram = masterProgram;
    }

    /**
     * Gets the program of the student, which corresponds to their master's program.
     *
     * @return the program of the student
     */
    @Override
    public Program getProgram() {
        return masterProgram;
    }

    /**
     * Gets the role of the student, which is {@code StudentRole.MASTER}.
     *
     * @return the role of the student
     */
    @Override
    public StudentRole getStudentRole() {
        return StudentRole.MASTER;
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
        getResearchProjects().add(researchProject);
    }

    /**
     * Provides a string representation of the {@code Master} student.
     *
     * @return a string containing details about the student
     */
    @Override
    public String toString() {
        return "Master{" +
                "masterProgram=" + masterProgram +
                "} " + super.toString();
    }
}
