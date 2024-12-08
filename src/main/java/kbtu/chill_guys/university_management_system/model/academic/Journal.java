package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.List;
import java.util.UUID;

public class Journal {

    private final UUID ID;
    private String name;
    private String description;
    private List<User> subscribers;
    private List<Post> posts;



    {
        ID = UUID.randomUUID();
    }

}
