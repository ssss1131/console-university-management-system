package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;

import java.util.Vector;

public class ResearcherDecorator <T extends User & CanBeResearcher> implements Researcher{

    private T User;
    private final Vector<ResearchProject> researchProjects = new Vector<>();
    private final Vector<ResearchPaper> researchPapers = new Vector<>();

    public T getUser() {
        return this.User;
    }

    public void setUser(T User) {
        this.User = User;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

}
