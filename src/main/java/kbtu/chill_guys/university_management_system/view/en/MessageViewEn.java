package main.java.kbtu.chill_guys.university_management_system.view.en;

import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.List;
import java.util.Scanner;

public class MessageViewEn implements MessageView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getReceiverEmail() {
        System.out.println("Enter the receiver's email: ");
        return scanner.nextLine();
    }

    @Override
    public String getMessageContent() {
        System.out.println("Enter your message: ");
        return scanner.nextLine();
    }

    @Override
    public void displayMessages(List<String> messages) {
        if (messages.isEmpty()) {
            System.out.println("You have no messages.");
        } else {
            System.out.println("Your messages:");
            for (String message : messages) {
                System.out.println("- " + message);
            }
        }
    }

    @Override
    public void displayMessageSent() {
        System.out.println("Message sent successfully.");
    }

    @Override
    public void displayError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}
