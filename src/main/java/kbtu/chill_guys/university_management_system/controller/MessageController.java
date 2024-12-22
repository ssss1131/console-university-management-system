package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.service.MessageService;
import java.util.*;

public class MessageController {
    private final MessageService messageService = new MessageService();

    public void sendMessage(String senderEmail, String receiverEmail, String message) {
        messageService.sendMessage(senderEmail, receiverEmail, message);
    }

    public List<String> viewMessages(String userEmail) {
        return messageService.viewMessages(userEmail);
    }
}
