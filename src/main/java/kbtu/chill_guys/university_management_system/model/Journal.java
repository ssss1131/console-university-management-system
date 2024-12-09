package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.util.UUID;
import java.util.Vector;

public class Journal {
    private String name;
    private UUID id;
    private Vector<UserSubscriber> subscribers;
    private Vector<Post> posts;

    public Journal() {}

    public Journal(String name, UUID id) {
        this.name = name;
        this.id = id != null ? id : UUID.randomUUID();
        this.subscribers = new Vector<>();
        this.posts = new Vector<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public Vector<UserSubscriber> getSubscribers() {
        return subscribers;
    }

    public Vector<Post> getPosts() {
        return posts;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSubscribers(Vector<UserSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    // Additional methods
    public boolean addSubscriber(UserSubscriber subscriber) {
        if (subscriber != null && !subscribers.contains(subscriber)) {
            return subscribers.add(subscriber);
        }
        return false;
    }

    public boolean removeSubscriber(UserSubscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    public void publish(Post post) {
        if (post != null) {
            posts.add(post);
            for (Subscriber subscriber : subscribers) {
                subscriber.update(post);
            }
        }
    }
}
