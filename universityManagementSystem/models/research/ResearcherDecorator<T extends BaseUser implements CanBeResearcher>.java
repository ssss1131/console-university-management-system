package universityManagementSystem.models.research;


import universityManagementSystem.models.BaseUser;
import universityManagementSystem.models.ResearchPaper;

/**
* @generated
*/
public class ResearcherDecorator<T extends BaseUser implements CanBeResearcher> implements Researcher {
    
    /**
    * @generated
    */
    private T User;
    
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
    private T getUser() {
        return this.User;
    }
    
    /**
    * @generated
    */
    private T setUser(T User) {
        this.User = User;
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
    
}
