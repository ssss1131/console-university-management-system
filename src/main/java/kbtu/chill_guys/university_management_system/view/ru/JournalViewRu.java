package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.view.JournalView;

import java.util.Scanner;
import java.util.UUID;

public class JournalViewRu implements JournalView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getJournalName() {
        System.out.println("Введите название журнала: ");
        return scanner.nextLine();
    }

    @Override
    public UUID getJournalId() {
        System.out.println("Введите ID журнала: ");
        return UUID.fromString(scanner.nextLine());
    }

    @Override
    public Post getPostInput() {
        System.out.println("Введите содержание поста: ");
        String content = scanner.nextLine();
        return new Post();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void postPublished() {
        System.out.println("Пост успешно опубликован!");
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