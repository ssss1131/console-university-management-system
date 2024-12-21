package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JournalViewRu implements JournalView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getNewJournalName() {
        System.out.println("Введите название журнала: ");
        return scanner.nextLine();
    }

    @Override
    public Journal getJournalForDeletion(List<Journal> journals) {
        if (journals == null || journals.isEmpty()) {
            System.out.println("Нет доступных журналов для удаления.");
            return null;
        }

        System.out.println("Выберите журнал для удаления:");
        for (int i = 0; i < journals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journals.get(i).getName());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, journals.size());
        return journals.get(choice - 1);
    }


    @Override
    public void journalCreated(UUID id) {
        System.out.println("Журнал успешно создан! UUID: " + id);
    }

    @Override
    public void journalDeleted() {
        System.out.println("Журнал успешно удален!");
    }
}