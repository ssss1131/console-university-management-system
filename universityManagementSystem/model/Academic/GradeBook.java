package universityManagementSystem.model.Academic;


import universityManagementSystem.Mark;
import universityManagementSystem.Student;

/**
* @generated
*/
public class GradeBook {
    
    /**
    * @generated
    */
    private Student student;
    
    /**
    * @generated
    */
    private universityManagementSystem.Mark mark;
    
    /**
    * @generated
    */
    private Map<Date, bool> attendance;
    
    
    
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
    private universityManagementSystem.Mark getMark() {
        return this.mark;
    }
    
    /**
    * @generated
    */
    private universityManagementSystem.Mark setMark(Mark mark) {
        this.mark = mark;
    }
    
    /**
    * @generated
    */
    private Map<Date, bool> getAttendance() {
        return this.attendance;
    }
    
    /**
    * @generated
    */
    private Map<Date, bool> setAttendance(Map<Date, bool> attendance) {
        this.attendance = attendance;
    }
    
}
