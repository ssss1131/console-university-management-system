package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class DeanViewRu implements DeanView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines) {
        Map<Discipline, Status> disciplineStatuses = new HashMap<>();

        if (allPendingDisciplines.isEmpty()) {
            System.out.println("Нет дисциплин для проверки.");
            return disciplineStatuses;
        }

        System.out.println("Просмотр дисциплин для проверки:");
        int index = 1;

        for (Discipline discipline : allPendingDisciplines) {
            System.out.printf("%d. Дисциплина: %s, Код: %s, Школа: %s, Кредиты: %d%n",
                    index++, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getCredits());
        }

        System.out.println("\nВведите номер каждой дисциплины для подтверждения или отклонения:\n");
        for (int i = 0; i < allPendingDisciplines.size(); i++) {
            Discipline discipline = allPendingDisciplines.get(i);
            while (true) {
                System.out.printf("Выберите статус для дисциплины \"%s\" (Код: %s):%n", discipline.getName(), discipline.getCode());
                System.out.println("1. ЗАЧИСЛЕНО");
                System.out.println("2. ОТМЕНЕНО");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    disciplineStatuses.put(discipline, Status.ASSIGNED);
                    break;
                } else if (choice.equals("2")) {
                    disciplineStatuses.put(discipline, Status.CANCELLED);
                    break;
                } else {
                    System.out.println("Неверный ввод. Пожалуйста, выберите 1 (ЗАЧИСЛЕНО) или 2 (ОТМЕНЕНО).");
                }
            }
        }

        System.out.println("Проверка завершена. Возвращение решений.");
        return disciplineStatuses;
    }
}