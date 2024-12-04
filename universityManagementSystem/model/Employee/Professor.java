package universityManagementSystem.model.Employee;


import universityManagementSystem.Teacher;
import universityManagementSystem.Vector;
import universityManagementSystem.model.ResearchPaper;
import universityManagementSystem.model.research.ResearchProject;

/**
* @generated
*/
public class Professor extends Teacher implements Researcher {
    
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
