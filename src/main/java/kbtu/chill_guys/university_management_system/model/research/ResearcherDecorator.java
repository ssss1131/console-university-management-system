package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;

import java.util.Vector;

public class ResearcherDecorator <T extends BaseUser> implements CanBeResearcher {
    private T User;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public T getUser() {
        return this.User;
    }

    public void setUser(T User) {
        this.User = User;
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
}
