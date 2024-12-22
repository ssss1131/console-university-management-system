package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.*;

public class DeanViewEn implements DeanView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines) {
        Map<Discipline, Status> disciplineStatuses = new HashMap<>();

        if (allPendingDisciplines.isEmpty()) {
            System.out.println("No pending disciplines for review.");
            return disciplineStatuses;
        }

        System.out.println("Review pending disciplines:");
        int index = 1;

        for (Discipline discipline : allPendingDisciplines) {
            System.out.printf("%d. Discipline: %s, Code: %s, School: %s, Credits: %d%n",
                    index++, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getCredits());
        }

        System.out.println("\nEnter the number of each discipline to approve or decline:\n");
        for (int i = 0; i < allPendingDisciplines.size(); i++) {
            Discipline discipline = allPendingDisciplines.get(i);
            while (true) {
                System.out.printf("Choose status for Discipline \"%s\" (Code: %s):%n", discipline.getName(), discipline.getCode());
                System.out.println("1. ASSIGNED");
                System.out.println("2. CANCELLED");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    disciplineStatuses.put(discipline, Status.ASSIGNED);
                    break;
                } else if (choice.equals("2")) {
                    disciplineStatuses.put(discipline, Status.CANCELLED);
                    break;
                } else {
                    System.out.println("Invalid input. Please choose 1 (ASSIGNED) or 2 (CANCELLED).");
                }
            }
        }

        System.out.println("Review completed. Returning decisions.");
        return disciplineStatuses;
    }

    @Override
    public Status selectComplaintStatus() {
        System.out.println("Select a complaint status:");
        return EnumSelectionUtil.selectEnum(Status.class);
    }

    @Override
    public UrgencyLevel selectUrgencyLevel() {
        System.out.println("Select an urgency level:");
        return EnumSelectionUtil.selectEnum(UrgencyLevel.class);
    }

    @Override
    public Complaint selectComplaint(List<Complaint> complaints) {
        System.out.println("Select a complaint:");
        for (int i = 0; i < complaints.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, complaints.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput("Enter your choice:", 1, complaints.size());
        return complaints.get(choice - 1);
    }

    @Override
    public boolean confirmComplaintAssignment() {
        System.out.println("Do you want to assign this complaint? (yes/no):");
        String input;
        while (true) {
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no':");
            }
        }
    }


    @Override
    public void showComplaintAssignedMessage(Complaint complaint) {
        System.out.printf("Complaint %s has been assigned successfully.%n", complaint.getId());
    }

    @Override
    public void showComplaintCancelledMessage(Complaint complaint) {
        System.out.printf("Complaint %s has been cancelled successfully.%n", complaint.getId());
    }

    @Override
    public void showNoComplaintsMessage(Status status) {
        System.out.printf("No complaints found with status: %s%n", status);
    }

    @Override
    public void showNoComplaintsWithUrgencyMessage(Status status, UrgencyLevel urgencyLevel) {
        System.out.printf("No complaints found with status: %s and urgency level: %s%n", status, urgencyLevel);
    }

    @Override
    public void showNoComplaintSelectedMessage() {
        System.out.println("No complaint selected.");
    }
}

