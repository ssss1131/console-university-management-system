package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.util.Vector;

public class GraduateStudent implements Researcher {
    private ResearchSupervisor supervisor;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public ResearchSupervisor getSupervisor() {
        return this.supervisor;
    }
    
    public void setSupervisor(ResearchSupervisor  supervisor) {
        this.supervisor = supervisor;
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
