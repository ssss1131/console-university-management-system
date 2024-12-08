package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class Post implements Serializable {
    private User author;
    private String content;
    private LocalDate date;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
