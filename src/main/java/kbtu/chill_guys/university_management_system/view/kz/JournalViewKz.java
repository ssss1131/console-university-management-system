package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JournalViewKz implements JournalView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getNewJournalName() {
        System.out.println("Журналдың атын енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public Journal getJournalForDeletion(List<Journal> journals) {
        if (journals == null || journals.isEmpty()) {
            System.out.println("Жоюға қолжетімді журналдар жоқ.");
            return null;
        }

        System.out.println("Журналды жою үшін таңдаңыз:");
        for (int i = 0; i < journals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journals.get(i).getName());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, journals.size());
        return journals.get(choice - 1);
    }


    @Override
    public void journalCreated(UUID id) {
        System.out.println("Журнал сәтті жасалды! UUID: " + id);
    }

    @Override
    public void journalDeleted() {
        System.out.println("Журнал сәтті жойылды!");
    }
}