package universityManagementSystem.model.Academic;


import universityManagementSystem.Post;
import universityManagementSystem.Student;

/**
* @generated
*/
public class Coomplaint extends Post {
    
    /**
    * @generated
    */
    private Student student;
    
    /**
    * @generated
    */
    private String description;
    
    /**
    * @generated
    */
    private Date date;
    
    
    
    /**
    * @generated
    */
    private Student getStudent() {
        return this.student;
    }
    
    /**
    * @generated
    */
    private Student setStudent(Student student) {
        this.student = student;
    }
    
    /**
    * @generated
    */
    private String getDescription() {
        return this.description;
    }
    
    /**
    * @generated
    */
    private String setDescription(String description) {
        this.description = description;
    }
    
    /**
    * @generated
    */
    private Date getDate() {
        return this.date;
    }
    
    /**
    * @generated
    */
    private Date setDate(Date date) {
        this.date = date;
    }
    
}
