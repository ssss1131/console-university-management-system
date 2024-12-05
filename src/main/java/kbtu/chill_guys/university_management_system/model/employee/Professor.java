package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.util.Vector;


public class Professor implements Researcher {
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

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
