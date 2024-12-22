package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.util.UUID;
import java.util.Vector;


/**
 * The {@code Professor} class represents a professor in the university management system.
 * It extends the {@link Teacher} class and implements the {@link Researcher} interface,
 * enabling the professor to engage in research activities and manage research projects and papers.
 */
public class Professor extends Teacher implements Researcher {

    /**
     * A collection of research projects the professor is involved in.
     */
    private final Vector<ResearchProject> researchProjects = new Vector<>();

    /**
     * A collection of research papers authored or co-authored by the professor.
     */
    private final Vector<ResearchPaper> researchPapers = new Vector<>();

    /**
     * Constructs a new {@code Professor} with specified attributes.
     *
     * @param id        the unique identifier of the professor
     * @param role      the role of the professor in the system
     * @param email     the email address of the professor
     * @param password  the password of the professor
     * @param salt      the salt used for password encryption
     * @param firstName the first name of the professor
     * @param lastName  the last name of the professor
     * @param salary    the salary of the professor
     * @param rating    the rating of the professor
     * @param school    the school the professor is affiliated with
     */
    public Professor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, int salary, int rating, School school) {
        super(id, role, email, password, salt, firstName, lastName, salary, rating, school);
    }

    /**
     * Gets the collection of research projects associated with the professor.
     *
     * @return the research projects
     */
    @Override
    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    /**
     * Adds a research paper to the professor's list of research papers.
     *
     * @param researchPaper the research paper to add
     */
    @Override
    public void addResearchPaper(ResearchPaper researchPaper) {
        this.researchPapers.add(researchPaper);
    }

    /**
     * Adds a research project to the professor's list of research projects.
     *
     * @param researchProject the research project to add
     */
    @Override
    public void addResearchProjects(ResearchProject researchProject) {
        this.researchProjects.add(researchProject);
    }

    /**
     * Gets the collection of research papers authored or co-authored by the professor.
     *
     * @return the research papers
     */
    @Override
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    /**
     * Provides a string representation of the {@code Professor} object.
     *
     * @return a string containing the professor's details
     */
    @Override
    public String toString() {
        return "Professor{" +
                "researchProjects=" + researchProjects +
                ", researchPapers=" + researchPapers +
                "} " + super.toString();
    }
}
