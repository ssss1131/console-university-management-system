package universityManagementSystem.model.research;


import universityManagementSystem.Vector;

/**
* @generated
*/
public class ResearchPaper {
    
    /**
    * @generated
    */
    private String title;
    
    /**
    * @generated
    */
    private String abstract;
    
    /**
    * @generated
    */
    private String journal;
    
    /**
    * @generated
    */
    private Integer citations;
    
    /**
    * @generated
    */
    private Vector<Researcher> authors;
    
    /**
    * @generated
    */
    private String doi;
    
    /**
    * @generated
    */
    private LocalDate publicationDate;
    
    
    
    /**
    * @generated
    */
    private String getTitle() {
        return this.title;
    }
    
    /**
    * @generated
    */
    private String setTitle(String title) {
        this.title = title;
    }
    
    /**
    * @generated
    */
    private String getAbstract() {
        return this.abstract;
    }
    
    /**
    * @generated
    */
    private String setAbstract(String abstract) {
        this.abstract = abstract;
    }
    
    /**
    * @generated
    */
    private String getJournal() {
        return this.journal;
    }
    
    /**
    * @generated
    */
    private String setJournal(String journal) {
        this.journal = journal;
    }
    
    /**
    * @generated
    */
    private Integer getCitations() {
        return this.citations;
    }
    
    /**
    * @generated
    */
    private Integer setCitations(Integer citations) {
        this.citations = citations;
    }
    
    /**
    * @generated
    */
    private Vector<Researcher> getAuthors() {
        return this.authors;
    }
    
    /**
    * @generated
    */
    private Vector<Researcher> setAuthors(Vector<Researcher> authors) {
        this.authors = authors;
    }
    
    /**
    * @generated
    */
    private String getDoi() {
        return this.doi;
    }
    
    /**
    * @generated
    */
    private String setDoi(String doi) {
        this.doi = doi;
    }
    
    /**
    * @generated
    */
    private LocalDate getPublicationDate() {
        return this.publicationDate;
    }
    
    /**
    * @generated
    */
    private LocalDate setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public String getCitation() {
        //TODO
        return "";
    }
    
    /**
    * @generated
    */
    public void addAuthor() {
        //TODO
        return null;
    }
    
    
}
