package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Vector;

/**
 * The {@code GraduateStudent} class represents a graduate-level student
 * in the university management system. It is an abstract class that extends
 * {@link Student} and implements the {@link Researcher} interface.
 * Graduate students are involved in research activities and maintain records
 * of their diploma project, research projects, and research papers.
 */
public abstract class GraduateStudent extends Student implements Researcher {

    /**
     * The diploma project associated with the graduate student.
     */
    private DiplomaProject project = new DiplomaProject();

    /**
     * A collection of research projects the student is involved in.
     */
    private Vector<ResearchProject> researchProjects = new Vector<>();

    /**
     * A collection of research papers authored or co-authored by the student.
     */
    private Vector<ResearchPaper> researchPapers = new Vector<>();

    /**
     * Constructs a {@code GraduateStudent} with all attributes, including organization.
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
     */
    public GraduateStudent(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, Organization organization) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration, organization);
    }

    /**
     * Constructs a {@code GraduateStudent} without an organization.
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
     */
    public GraduateStudent(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration);
    }

    /**
     * Gets the diploma project of the graduate student.
     *
     * @return the diploma project
     */
    public DiplomaProject getProject() {
        return project;
    }

    /**
     * Sets the diploma project for the graduate student.
     *
     * @param project the new diploma project
     */
    public void setProject(DiplomaProject project) {
        this.project = project;
    }

    /**
     * Gets the collection of research projects associated with the student.
     *
     * @return the research projects
     */
    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    /**
     * Sets the collection of research projects for the student.
     *
     * @param researchProjects the new collection of research projects
     */
    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    /**
     * Gets the collection of research papers authored by the student.
     *
     * @return the research papers
     */
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    /**
     * Sets the collection of research papers for the student.
     *
     * @param researchPapers the new collection of research papers
     */
    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    /**
     * Provides a string representation of the {@code GraduateStudent}.
     *
     * @return a string containing details about the graduate student
     */
    @Override
    public String toString() {
        return "GraduateStudent{" +
                "project=" + project +
                ", researchProjects=" + researchProjects +
                ", researchPapers=" + researchPapers +
                "} " + super.toString();
    }
}
