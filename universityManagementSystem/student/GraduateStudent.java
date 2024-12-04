package universityManagementSystem.student;


import universityManagementSystem.ResearchSupervisor;
import universityManagementSystem.Student;
import universityManagementSystem.Vector;

/**
* @generated
*/
public class GraduateStudent extends Student implements Researcher {
    
    /**
    * @generated
    */
    private ResearchSupervisor supervisor;
    
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
    private ResearchSupervisor  getSupervisor() {
        return this.supervisor;
    }
    
    /**
    * @generated
    */
    private ResearchSupervisor  setSupervisor(ResearchSupervisor  supervisor) {
        this.supervisor = supervisor;
    }
    
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
    

    //                          Operations                                  
    
    
}
