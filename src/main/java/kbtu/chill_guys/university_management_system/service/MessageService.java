package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.employee.Employee;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Message;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.MessageStorage;

import java.util.*;

public class MessageService {
    private final UserRepository userRepository;
    private final MessageStorage messageStorage;

    public MessageService() {
        this.userRepository = new UserRepository();
        this.messageStorage = MessageStorage.getInstance();
    }

    public void sendMessage(String senderEmail, String receiverEmail, String content) {
        Employee sender = (Employee) userRepository.findUserByEmail(senderEmail);
        Employee receiver = (Employee) userRepository.findUserByEmail(receiverEmail);

        if (sender == null) {
            throw new IllegalArgumentException("Sender not found: " + senderEmail);
        }
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver not found: " + receiverEmail);
        }
        if (!isEmployee(sender) || !isEmployee(receiver)) {
            throw new IllegalArgumentException("Both sender and receiver must be employees");
        }

        Message message = new Message(sender, receiver, content);
        messageStorage.sendMessage(message);
    }

    public List<String> viewMessages(String userEmail) {
        Employee user = (Employee) userRepository.findUserByEmail(userEmail);

        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userEmail);
        }
        if (!isEmployee(user)) {
            throw new IllegalArgumentException("User must be an employee");
        }

        return messageStorage.getMessages(userEmail).stream()
                .map(message -> String.format("From: %s (%s)\nTime: %s\nMessage: %s",
                        message.getSender().getFirstName() + " " + message.getSender().getLastName(),
                        message.getSender().getEmail(),
                        message.getTimestamp(),
                        message.getContent()))
                .toList();
    }

    private boolean isEmployee(Object user) {
        return user instanceof Employee;
    }
}