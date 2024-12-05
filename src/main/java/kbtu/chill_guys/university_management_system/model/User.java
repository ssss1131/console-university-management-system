package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.Vector;

public interface User {
    boolean login(String username, String password);
    Vector<Post> viewNews();
    void subscribeJournal(Journal journal);
    void unsubscribeJournal(Journal journal);
}
