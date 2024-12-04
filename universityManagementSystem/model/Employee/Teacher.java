package universityManagementSystem.model.Employee;


import universityManagementSystem.Employee;

/**
* @generated
*/
public class Teacher extends Employee implements CanViewCourses, CanViewStudents {
    
    /**
    * @generated
    */
    private invalid faculty;
    
    /**
    * @generated
    */
    private Rating rating;
    
    /**
    * @generated
    */
    private invalid status;
    
    /**
    * @generated
    */
    private TeachingDegree teachingDegree;
    
    
    
    /**
    * @generated
    */
    private invalid getFaculty() {
        return this.faculty;
    }
    
    /**
    * @generated
    */
    private invalid setFaculty(invalid faculty) {
        this.faculty = faculty;
    }
    
    /**
    * @generated
    */
    private Rating getRating() {
        return this.rating;
    }
    
    /**
    * @generated
    */
    private Rating setRating(Rating rating) {
        this.rating = rating;
    }
    
    /**
    * @generated
    */
    private invalid getStatus() {
        return this.status;
    }
    
    /**
    * @generated
    */
    private invalid setStatus(invalid status) {
        this.status = status;
    }
    
    /**
    * @generated
    */
    private TeachingDegree getTeachingDegree() {
        return this.teachingDegree;
    }
    
    /**
    * @generated
    */
    private TeachingDegree setTeachingDegree(TeachingDegree teachingDegree) {
        this.teachingDegree = teachingDegree;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void sendComplaints() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public int putMarks() {
        //TODO
        return 0;
    }
    
    /**
    * @generated
    */
    public boolean sendComplaints() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public boolean putMarks() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public void viewMarks() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public void getRating() {
        //TODO
        return null;
    }
    
    
}
