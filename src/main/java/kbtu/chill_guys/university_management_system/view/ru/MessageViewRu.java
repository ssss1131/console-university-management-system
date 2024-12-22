package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.List;
import java.util.Scanner;

public class MessageViewRu implements MessageView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getReceiverEmail() {
        System.out.println("Введите почту получателя: ");
        return scanner.nextLine();
    }

    @Override
    public String getMessageContent() {
        System.out.println("Введите сообщение: ");
        return scanner.nextLine();
    }

    @Override
    public void displayMessages(List<String> messages) {
        if (messages.isEmpty()) {
            System.out.println("У вас нет сообщений.");
        } else {
            System.out.println("Ваши сообщения:");
            for (String message : messages) {
                System.out.println("- " + message);
            }
        }
    }

    @Override
    public void displayMessageSent() {
        System.out.println("Сообщение отправлено.");
    }

    @Override
    public void displayError(String errorMessage) {
        System.out.println("Ошибка: " + errorMessage);
    }
}
