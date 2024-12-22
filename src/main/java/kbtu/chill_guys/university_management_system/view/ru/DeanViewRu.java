package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.*;

public class DeanViewRu implements DeanView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines) {
        Map<Discipline, Status> disciplineStatuses = new HashMap<>();

        if (allPendingDisciplines.isEmpty()) {
            System.out.println("Нет дисциплин, ожидающих проверки.");
            return disciplineStatuses;
        }

        System.out.println("Просмотр дисциплин на утверждение:");
        int index = 1;

        for (Discipline discipline : allPendingDisciplines) {
            System.out.printf("%d. Дисциплина: %s, Код: %s, Школа: %s, Кредиты: %d%n",
                    index++, discipline.getName(), discipline.getCode(), discipline.getSchool(), discipline.getCredits());
        }

        System.out.println("\nВведите номер каждой дисциплины для утверждения или отклонения:\n");
        for (int i = 0; i < allPendingDisciplines.size(); i++) {
            Discipline discipline = allPendingDisciplines.get(i);
            while (true) {
                System.out.printf("Выберите статус для дисциплины \"%s\" (Код: %s):%n", discipline.getName(), discipline.getCode());
                System.out.println("1. Утверждено");
                System.out.println("2. Отменено");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    disciplineStatuses.put(discipline, Status.ASSIGNED);
                    break;
                } else if (choice.equals("2")) {
                    disciplineStatuses.put(discipline, Status.CANCELLED);
                    break;
                } else {
                    System.out.println("Неверный ввод. Пожалуйста, выберите 1 (Утверждено) или 2 (Отменено).");
                }
            }
        }

        System.out.println("Рассмотрение завершено. Возврат решений.");
        return disciplineStatuses;
    }

    @Override
    public Status selectComplaintStatus() {
        System.out.println("Выберите статус жалобы:");
        return EnumSelectionUtil.selectEnum(Status.class);
    }

    @Override
    public UrgencyLevel selectUrgencyLevel() {
        System.out.println("Выберите уровень срочности:");
        return EnumSelectionUtil.selectEnum(UrgencyLevel.class);
    }

    @Override
    public Complaint selectComplaint(List<Complaint> complaints) {
        System.out.println("Выберите жалобу:");
        for (int i = 0; i < complaints.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, complaints.get(i));
        }

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор:", 1, complaints.size());
        return complaints.get(choice - 1);
    }

    @Override
    public boolean confirmComplaintAssignment() {
        System.out.println("Вы хотите назначить эту жалобу? (да/нет):");
        String input;
        while (true) {
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("да")) {
                return true;
            } else if (input.equals("нет")) {
                return false;
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите 'да' или 'нет':");
            }
        }
    }

    @Override
    public void showComplaintAssignedMessage(Complaint complaint) {
        System.out.printf("Жалоба %s успешно назначена.%n", complaint.getId());
    }

    @Override
    public void showComplaintCancelledMessage(Complaint complaint) {
        System.out.printf("Жалоба %s успешно отменена.%n", complaint.getId());
    }

    @Override
    public void showNoComplaintsMessage(Status status) {
        System.out.printf("Жалобы со статусом %s не найдены.%n", status);
    }

    @Override
    public void showNoComplaintsWithUrgencyMessage(Status status, UrgencyLevel urgencyLevel) {
        System.out.printf("Жалобы со статусом %s и уровнем срочности %s не найдены.%n", status, urgencyLevel);
    }

    @Override
    public void showNoComplaintSelectedMessage() {
        System.out.println("Жалоба не выбрана.");
    }
}
