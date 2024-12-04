package universityManagementSystem.model.Employee;


import universityManagementSystem.Student;
import universityManagementSystem.Teacher;
import universityManagementSystem.User;
import universityManagementSystem.Vector;

/**
* @generated
*/
public class Employee extends BaseUserBaseUser implements User, CanViewCourses {
    
    /**
    * @generated
    */
    private Integer salary;
    
    
    /**
    * @generated
    */
    private Teacher teacher;
    
    
    /**
    * @generated
    */
    private Integer getSalary() {
        return this.salary;
    }
    
    /**
    * @generated
    */
    private Integer setSalary(Integer salary) {
        this.salary = salary;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void sendMessage() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public Vector<Student> viewStudents() {
        //TODO
        return null;
    }
    
    
}
