package universityManagementSystem.model.research;


import universityManagementSystem.Vector;
import universityManagementSystem.model.ResearchPaper;

/**
* @generated
*/
public class ResearchProject {
    
    /**
    * @generated
    */
    private String topic;
    
    /**
    * @generated
    */
    private Vector<Researcher> participants;
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.model.ResearchPaper> publishedPapers;
    
    
    
    /**
    * @generated
    */
    private String getTopic() {
        return this.topic;
    }
    
    /**
    * @generated
    */
    private String setTopic(String topic) {
        this.topic = topic;
    }
    
    /**
    * @generated
    */
    private Vector<Researcher> getParticipants() {
        return this.participants;
    }
    
    /**
    * @generated
    */
    private Vector<Researcher> setParticipants(Vector<Researcher> participants) {
        this.participants = participants;
    }
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.model.ResearchPaper> getPublishedPapers() {
        return this.publishedPapers;
    }
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.model.ResearchPaper> setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void joinToProject() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public void quitFromProject() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public boolean publishPaper() {
        //TODO
        return false;
    }
    
    
}