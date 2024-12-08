package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Journal implements Serializable {

    private String name;
    private String description;
    private final Vector<User> subscribers = new Vector<>();
    private final Vector<Post> posts = new Vector<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<User> getSubscribers() {
        return subscribers;
    }


    public Vector<Post> getPosts() {
        return posts;
    }

    public void addSubscriber(User user){
        this.subscribers.add(user);
    }

    public void addPost(Post post){
        this.posts.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return Objects.equals(name, journal.name) && Objects.equals(description, journal.description) && Objects.equals(subscribers, journal.subscribers) && Objects.equals(posts, journal.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, subscribers, posts);
    }


    @Override
    public String toString() {
        return "Journal{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", subscribers=" + subscribers +
               ", posts=" + posts +
               '}';
    }
}
