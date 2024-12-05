package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;

import java.time.LocalDate;

public class Post {
    private LocalDate date;
    private String content;
    private BaseUser author;

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

    public BaseUser getAuthor() {
        return this.author;
    }

    public void setAuthor(BaseUser author) {
        this.author = author;
    }
}
