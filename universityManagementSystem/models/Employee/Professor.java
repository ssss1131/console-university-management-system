package universityManagementSystem.models.Employee;


import universityManagementSystem.Vector;
import universityManagementSystem.models.ResearchPaper;
import universityManagementSystem.models.research.ResearchProject;

/**
* @generated
*/
public class Professor implements Researcher {
    
    /**
    * @generated
    */
    private Vector<ResearchProject> researchProjects;
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> researchPapers;
    
    
    
    /**
    * @generated
    */
    private Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchProject> setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
    
}
