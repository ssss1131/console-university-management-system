package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.storage.ComplaintStorage;

import java.util.List;
import java.util.stream.Collectors;

public class ComplaintService {
    private final ComplaintStorage storage = ComplaintStorage.getInstance();

    public List<Complaint> getComplaintsByStatus(Status status) {
        return storage.getComplaints().stream()
                .filter(complaint -> complaint.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Complaint> getComplaintsByStatusAndUrgency(Status status, UrgencyLevel urgencyLevel) {
        return storage.getComplaints().stream()
                .filter(complaint -> complaint.getStatus() == status && complaint.getUrgencyLevel() == urgencyLevel)
                .collect(Collectors.toList());
    }

    public void assignComplaint(Complaint complaint) {
        complaint.assign();
        storage.saveToFile();
    }

    public void cancelComplaint(Complaint complaint) {
        complaint.reject();
        storage.saveToFile();
    }

    public void createComplaint(Teacher teacher, Student student, Discipline discipline, String comment, UrgencyLevel urgencyLevel) {
        Complaint complaint = new Complaint(teacher, student, discipline, comment, urgencyLevel);
        storage.addComplaint(complaint);
    }
}
