package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;

import java.util.Vector;

public final class ResearcherDecorator <T extends User & CanBeResearcher> implements Researcher{

    private final T User;
    private final Vector<ResearchProject> researchProjects = new Vector<>();
    private final Vector<ResearchPaper> researchPapers = new Vector<>();

    public ResearcherDecorator(T user) {
        User = user;
    }

    public T getUser() {
        return this.User;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    @Override
    public void addResearchPaper(ResearchProject researchProject) {

    }

    @Override
    public void addResearchProjects(ResearchProject researchProject) {

    }

    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

}
