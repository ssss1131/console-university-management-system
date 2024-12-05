package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.Vector;

public class Dean implements CanViewRequests, CanViewStudents {
    public Vector<Complaint> ViewComplaints() {
        //TODO
        return null;
    }
    
    public void SignRequest() {
        //TODO
    }
    
    public void RejectRequest() {
        //TODO
    }
}
