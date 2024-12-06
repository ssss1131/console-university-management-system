package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.DeanService;

import java.util.Vector;
public class  DeanController {
    private DeanService deanService;

    public DeanService getDeanService() {
        return this.deanService;
    }
    public void setDeanService(DeanService deanService) {
        this.deanService = deanService;
    }

    public Vector<Complaint> getComplaints() {
        //TODO
        return null;
    }

    public Vector<Student> getStudents() {
        //TODO
        return null;
    }

    public void sendMessage() {
        //TODO
    }

    public void approveRequest() {
        //TODO
    }

    public void declineRequest() {
        //TODO
    }
}
