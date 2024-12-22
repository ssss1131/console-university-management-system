package main.java.kbtu.chill_guys.university_management_system.model.employee;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private final Employee sender;
    private final Employee recipient;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(Employee sender, Employee recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public Employee getSender() {
        return sender;
    }

    public Employee getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
