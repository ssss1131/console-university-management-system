package universityManagementSystem.models.student;

import universityManagementSystem.models.employee.ResearchSupervisor;
import universityManagementSystem.models.research.ResearchPaper;
import universityManagementSystem.models.student.research.ResearchProject;
import universityManagementSystem.models.student.research.Researcher;

import java.util.Vector;

public class GraduateStudent implements Researcher {
    private ResearchSupervisor supervisor;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    private ResearchSupervisor getSupervisor() {
        return this.supervisor;
    }
    
    private void setSupervisor(ResearchSupervisor  supervisor) {
        this.supervisor = supervisor;
    }
    
    private Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }
    
    private void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }
    
    private Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }
    
    private void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
}
