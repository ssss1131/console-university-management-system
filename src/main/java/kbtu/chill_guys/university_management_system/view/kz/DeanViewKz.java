package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.*;

public class DeanViewKz implements DeanView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines) {
        Map<Discipline, Status> disciplineStatuses = new HashMap<>();

        if (allPendingDisciplines.isEmpty()) {
            System.out.println("Қаралуға арналған пәндер жоқ.");
            return disciplineStatuses;
        }

        System.out.println("Қаралуға арналған пәндерді шолу:");
        int index = 1;

        for (Discipline discipline : allPendingDisciplines) {
            System.out.printf("%d. Пән: %s, Код: %s, Мектеп: %s, Несие: %d%n",
                    index++, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getCredits());
        }

        System.out.println("\nӘр пән үшін мақұлдау немесе бас тарту үшін нөмірді енгізіңіз:\n");
        for (int i = 0; i < allPendingDisciplines.size(); i++) {
            Discipline discipline = allPendingDisciplines.get(i);
            while (true) {
                System.out.printf("Пән үшін мәртебені таңдаңыз \"%s\" (Код: %s):%n", discipline.getName(), discipline.getCode());
                System.out.println("1. Бекітілді");
                System.out.println("2. Бас тартылды");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    disciplineStatuses.put(discipline, Status.ASSIGNED);
                    break;
                } else if (choice.equals("2")) {
                    disciplineStatuses.put(discipline, Status.CANCELLED);
                    break;
                } else {
                    System.out.println("Қате енгізу. 1 (Бекітілді) немесе 2 (Бас тартылды) таңдаңыз.");
                }
            }
        }

        System.out.println("Қарау аяқталды. Шешімдер қайтарылады.");
        return disciplineStatuses;
    }

    @Override
    public Status selectComplaintStatus() {
        System.out.println("Шағымның мәртебесін таңдаңыз:");
        return EnumSelectionUtil.selectEnum(Status.class);
    }

    @Override
    public UrgencyLevel selectUrgencyLevel() {
        System.out.println("Шұғылдық деңгейін таңдаңыз:");
        return EnumSelectionUtil.selectEnum(UrgencyLevel.class);
    }

    @Override
    public Complaint selectComplaint(List<Complaint> complaints) {
        System.out.println("Шағымды таңдаңыз:");
        for (int i = 0; i < complaints.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, complaints.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз:", 1, complaints.size());
        return complaints.get(choice - 1);
    }

    @Override
    public boolean confirmComplaintAssignment() {
        System.out.println("Сіз бұл шағымды тағайындауды қалайсыз ба? (иә/жоқ):");
        String input;
        while (true) {
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("иә")) {
                return true;
            } else if (input.equals("жоқ")) {
                return false;
            } else {
                System.out.println("Қате енгізу. Өтінемін, 'иә' немесе 'жоқ' деп енгізіңіз:");
            }
        }
    }

    @Override
    public void showComplaintAssignedMessage(Complaint complaint) {
        System.out.printf("Шағым %s сәтті тағайындалды.%n", complaint.getId());
    }

    @Override
    public void showComplaintCancelledMessage(Complaint complaint) {
        System.out.printf("Шағым %s сәтті жойылды.%n", complaint.getId());
    }

    @Override
    public void showNoComplaintsMessage(Status status) {
        System.out.printf("Мәртебесі бар шағымдар табылмады: %s%n", status);
    }

    @Override
    public void showNoComplaintsWithUrgencyMessage(Status status, UrgencyLevel urgencyLevel) {
        System.out.printf("Мәртебесі %s және шұғылдық деңгейі %s бар шағымдар табылмады.%n", status, urgencyLevel);
    }

    @Override
    public void showNoComplaintSelectedMessage() {
        System.out.println("Шағым таңдалмады.");
    }
}
