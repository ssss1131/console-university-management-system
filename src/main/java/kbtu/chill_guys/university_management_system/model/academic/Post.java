package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.time.LocalDate;

public class Post {
    private String title;
    private LocalDate date;
    private String content;
    private User author;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
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

    public String toString() {
        return "Title: " + getTitle() + " Date: " + getDate() + " Content: " + getContent() + " Author: " + getAuthor();
    }
}
