package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.util.UUID;
import java.util.Vector;


public class Professor extends Teacher implements Researcher {
    private final Vector<ResearchProject> researchProjects = new Vector<>();
    private final Vector<ResearchPaper> researchPapers = new Vector<>();


    public Professor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, int salary, int rating, School school) {
        super(id, role, email, password, salt, firstName, lastName, salary, rating, school);
    }

    @Override
    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    @Override
    public void addResearchPaper(ResearchPaper researchPaper) {
        this.researchPapers.add(researchPaper);
    }


    @Override
    public void addResearchProjects(ResearchProject researchProject) {
        this.researchProjects.add(researchProject);
    }

    @Override
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }


    @Override
    public String toString() {
        return "Professor{" +
               "researchProjects=" + researchProjects +
               ", researchPapers=" + researchPapers +
               "} " + super.toString();
    }
}
