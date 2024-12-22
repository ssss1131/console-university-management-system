package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.List;
import java.util.Scanner;

public class MessageViewKz implements MessageView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getReceiverEmail() {
        System.out.println("Алушының электрондық поштасын енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public String getMessageContent() {
        System.out.println("Хабарламаңызды енгізіңіз: ");
        return scanner.nextLine();
    }

    @Override
    public void displayMessages(List<String> messages) {
        if (messages.isEmpty()) {
            System.out.println("Сізде хабар жоқ.");
        } else {
            System.out.println("Сіздің хабарламаларыңыз:");
            for (String message : messages) {
                System.out.println("- " + message);
            }
        }
    }

    @Override
    public void displayMessageSent() {
        System.out.println("Хабар жіберілді.");
    }

    @Override
    public void displayError(String errorMessage) {
        System.out.println("Қате: " + errorMessage);
    }
}
