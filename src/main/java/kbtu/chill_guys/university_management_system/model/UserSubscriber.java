package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

public class UserSubscriber implements Subscriber {
    private final User user;

    public UserSubscriber(User user) {
        this.user = user;
    }

    public void update(Post post) {
        user.getNotifications().add(post);
    }
}
