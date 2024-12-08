package main.java.kbtu.chill_guys.university_management_system.model.academic;

import java.util.Vector;

public class News extends Post {
    private String topic;
    private Vector<Post> comments;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Vector<Post> getComments() {
        return this.comments;
    }

    public void setComments(Vector<Post> comments) {
        this.comments = comments;
    }
}
