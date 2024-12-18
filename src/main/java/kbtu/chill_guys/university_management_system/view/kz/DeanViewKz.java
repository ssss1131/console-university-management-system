package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class DeanViewKz implements DeanView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines) {
        Map<Discipline, Status> disciplineStatuses = new HashMap<>();

        if (allPendingDisciplines.isEmpty()) {
            System.out.println("Қарауға арналған пәндер жоқ.");
            return disciplineStatuses;
        }

        System.out.println("Қарауға арналған пәндерді шолу:");
        int index = 1;

        for (Discipline discipline : allPendingDisciplines) {
            System.out.printf("%d. Пән: %s, Код: %s, Мектеп: %s, Кредиттер: %d%n",
                    index++, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getCredits());
        }

        System.out.println("\nӘр пәнге бекіту немесе бас тарту үшін нөмір енгізіңіз:\n");
        for (int i = 0; i < allPendingDisciplines.size(); i++) {
            Discipline discipline = allPendingDisciplines.get(i);
            while (true) {
                System.out.printf("Пән үшін мәртебені таңдаңыз \"%s\" (Код: %s):%n", discipline.getName(), discipline.getCode());
                System.out.println("1. БЕКІТІЛДІ");
                System.out.println("2. БАС ТАРТЫЛДЫ");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    disciplineStatuses.put(discipline, Status.ASSIGNED);
                    break;
                } else if (choice.equals("2")) {
                    disciplineStatuses.put(discipline, Status.CANCELLED);
                    break;
                } else {
                    System.out.println("Қате енгізу. 1 (БЕКІТІЛДІ) немесе 2 (БАС ТАРТЫЛДЫ) таңдаңыз.");
                }
            }
        }

        System.out.println("Шолу аяқталды. Шешімдерді қайтару.");
        return disciplineStatuses;
    }
}