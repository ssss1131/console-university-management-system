package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class DeanView {
    private final Scanner scanner = new Scanner(System.in);

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
}

