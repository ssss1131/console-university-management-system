package universityManagementSystem.models.employee;

import universityManagementSystem.models.research.ResearchProject;
import universityManagementSystem.models.research.ResearchPaper;
import universityManagementSystem.models.student.research.Researcher;

import java.util.Vector;


public class Professor implements Researcher {
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

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
