package main.java.kbtu.chill_guys.university_management_system.menu.dean_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.ComplaintService;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.List;

public class ManageComplaintCommand implements Command {
    private final ComplaintService complaintService = new ComplaintService();

    @Override
    public void execute() {
        DeanView view = ViewFactory.getDeanView(Menu.getInstance().getLanguage());

        Status selectedStatus = view.selectComplaintStatus();

        List<Complaint> filteredComplaints = complaintService.getComplaintsByStatus(selectedStatus);

        if (filteredComplaints.isEmpty()) {
            view.showNoComplaintsMessage(selectedStatus);
            return;
        }

        if (selectedStatus == Status.PENDING_APPROVAL) {
            UrgencyLevel urgencyLevel = view.selectUrgencyLevel();
            filteredComplaints = complaintService.getComplaintsByStatusAndUrgency(selectedStatus, urgencyLevel);

            if (filteredComplaints.isEmpty()) {
                view.showNoComplaintsWithUrgencyMessage(selectedStatus, urgencyLevel);
                return;
            }
        }

        Complaint selectedComplaint = view.selectComplaint(filteredComplaints);

        if (selectedComplaint == null) {
            view.showNoComplaintSelectedMessage();
            return;
        }

        boolean assign = view.confirmComplaintAssignment();
        if (assign) {
            complaintService.assignComplaint(selectedComplaint);
            view.showComplaintAssignedMessage(selectedComplaint);
        } else {
            complaintService.cancelComplaint(selectedComplaint);
            view.showComplaintCancelledMessage(selectedComplaint);
        }
    }
}
