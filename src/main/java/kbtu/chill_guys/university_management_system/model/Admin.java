package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.UUID;
import java.util.Vector;

public class Admin extends BaseUser {
    public Admin(UUID id, String email, String firstName, String lastName, Vector<Post> notifications) {
        super(id, email, firstName, lastName, notifications);
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public Vector<Post> viewNews() {
        return null;
    }

    @Override
    public void subscribeJournal(Journal journal) {

    }

    @Override
    public void unsubscribeJournal(Journal journal) {

    }
}
