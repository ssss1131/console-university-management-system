package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.Vector;

public class Journal {
    private String name;
    private Vector<User> subscibers;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<User> getSubscibers() {
        return this.subscibers;

    }
    public void setSubscibers(Vector<User> subscibers) {
        this.subscibers = subscibers;
    }

    public void notify(Post post) {
        //TODO
    }

    public void publish() {
        //TODO
    }

    public boolean add() {
        //TODO
        return false;
    }

    public boolean remove() {
        //TODO
        return false;
    }
}
