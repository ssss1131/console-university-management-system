package universityManagementSystem.models;

import universityManagementSystem.models.academic.Post;

import java.util.Vector;

public class Journal {
    private String name;
    private Vector<User> subscibers;

    
    private String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private Vector<User> getSubscibers() {
        return this.subscibers;

    }
    private void setSubscibers(Vector<User> subscibers) {
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
