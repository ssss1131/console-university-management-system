package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.Scanner;
import java.util.UUID;

public class JournalViewKz implements JournalView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getJournalName() {
        System.out.println("Журналдың атын енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public UUID getJournalId() {
        System.out.println("Журналдың ID-ін енгізіңіз: ");
        return UUID.fromString(scanner.nextLine());
    }

    @Override
    public Post getPostInput() {
        System.out.println("Посттың мазмұнын енгізіңіз: ");
        String content = scanner.nextLine();
        return new Post();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void postPublished() {
        System.out.println("Пост сәтті жарияланды!");
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