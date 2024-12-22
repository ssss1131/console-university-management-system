package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.model.employee.Message;
import java.io.Serializable;
import java.util.*;

public class MessageStorage implements Serializable {
    private static final MessageStorage instance = new MessageStorage();
    private final Map<String, List<Message>> messagesByEmail = new HashMap<>();

    private MessageStorage() {}

    public static MessageStorage getInstance() {
        return instance;
    }

    public void sendMessage(Message message) {
        String receiverEmail = message.getRecipient().getEmail();
        messagesByEmail.computeIfAbsent(receiverEmail, k -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessages(String receiverEmail) {
        return messagesByEmail.getOrDefault(receiverEmail, new ArrayList<>());
    }

    public void clearMessages(String email) {
        messagesByEmail.remove(email);
    }
}