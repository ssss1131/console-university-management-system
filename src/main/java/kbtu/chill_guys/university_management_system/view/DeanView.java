package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface DeanView {
    Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines);

    Status selectComplaintStatus();

    UrgencyLevel selectUrgencyLevel();

    Complaint selectComplaint(List<Complaint> complaints);

    boolean confirmComplaintAssignment();

    void showComplaintAssignedMessage(Complaint complaint);

    void showComplaintCancelledMessage(Complaint complaint);

    void showNoComplaintsMessage(Status status);

    void showNoComplaintsWithUrgencyMessage(Status status, UrgencyLevel urgencyLevel);

    void showNoComplaintSelectedMessage();
}

