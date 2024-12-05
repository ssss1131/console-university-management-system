package main.java.kbtu.chill_guys.university_management_system.model.academic;


import java.util.Vector;

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
    private void setPeriod(Period period) {
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
    private void setLimit (Integer limit ) {
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
    private void setYear(Integer year) {
        this.year = year;
    }
    

    /**
    * @generated
    */
    public void displayReport() {
        //TODO
    }
    
    
}
