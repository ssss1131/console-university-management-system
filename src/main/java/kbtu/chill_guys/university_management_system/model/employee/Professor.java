package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;


public class Professor extends Teacher implements Researcher {
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;


    public Professor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, int salary, int rating, School school, TeachingDegree teachingDegree) {
        super(id, role, email, password, salt, firstName, lastName, notifications, salary, rating, school, teachingDegree);
    }

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }


    @Override
    public String toString() {
        return "Professor{" +
               "researchProjects=" + researchProjects +
               ", researchPapers=" + researchPapers +
               "} " + super.toString();
    }
}
