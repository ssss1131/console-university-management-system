package universityManagementSystem.model.Employee;


import universityManagementSystem.Employee;
import universityManagementSystem.Vector;
import universityManagementSystem.model.ResearchPaper;
import universityManagementSystem.model.research.ResearchProject;
import universityManagementSystem.student.GraduateStudent;

/**
* @generated
*/
public class ResearchSupervisor  extends Employee implements Researcher {
    
    /**
    * @generated
    */
    private Integer hIndex;
    
    /**
    * @generated
    */
    private Vector<ResearchProject> supervisedProjects ;
    
    /**
    * @generated
    */
    private Vector<GraduateStudent> supervisedStudents ;
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> supervisedResearchPapers;
    
    
    
    /**
    * @generated
    */
    private Integer getHIndex() {
        return this.hIndex;
    }
    
    /**
    * @generated
    */
    private Integer setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchProject> getSupervisedProjects () {
        return this.supervisedProjects ;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchProject> setSupervisedProjects (Vector<ResearchProject> supervisedProjects ) {
        this.supervisedProjects  = supervisedProjects ;
    }
    
    /**
    * @generated
    */
    private Vector<GraduateStudent> getSupervisedStudents () {
        return this.supervisedStudents ;
    }
    
    /**
    * @generated
    */
    private Vector<GraduateStudent> setSupervisedStudents (Vector<GraduateStudent> supervisedStudents ) {
        this.supervisedStudents  = supervisedStudents ;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> getSupervisedResearchPapers() {
        return this.supervisedResearchPapers;
    }
    
    /**
    * @generated
    */
    private Vector<ResearchPaper> setSupervisedResearchPapers(Vector<ResearchPaper> supervisedResearchPapers) {
        this.supervisedResearchPapers = supervisedResearchPapers;
    }
    
}
