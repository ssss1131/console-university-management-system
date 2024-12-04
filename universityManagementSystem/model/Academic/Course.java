package universityManagementSystem.model.Academic;


import universityManagementSystem.Vector;

/**
* @generated
*/
public class Course extends Subject {
    
    /**
    * @generated
    */
    private Vector<Subject> subjects;
    
    /**
    * @generated
    */
    private Period period;
    
    /**
    * @generated
    */
    private Integer limit ;
    
    /**
    * @generated
    */
    private Integer year;
    
    /**
    * @generated
    */
    private invalid gradebook;
    
    
    
    /**
    * @generated
    */
    private Vector<Subject> getSubjects() {
        return this.subjects;
    }
    
    /**
    * @generated
    */
    private Vector<Subject> setSubjects(Vector<Subject> subjects) {
        this.subjects = subjects;
    }
    
    /**
    * @generated
    */
    private Period getPeriod() {
        return this.period;
    }
    
    /**
    * @generated
    */
    private Period setPeriod(Period period) {
        this.period = period;
    }
    
    /**
    * @generated
    */
    private Integer getLimit () {
        return this.limit ;
    }
    
    /**
    * @generated
    */
    private Integer setLimit (Integer limit ) {
        this.limit  = limit ;
    }
    
    /**
    * @generated
    */
    private Integer getYear() {
        return this.year;
    }
    
    /**
    * @generated
    */
    private Integer setYear(Integer year) {
        this.year = year;
    }
    
    /**
    * @generated
    */
    private invalid getGradebook() {
        return this.gradebook;
    }
    
    /**
    * @generated
    */
    private invalid setGradebook(invalid gradebook) {
        this.gradebook = gradebook;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void displayReport() {
        //TODO
        return null;
    }
    
    
}
