package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;

import java.time.LocalDate;
import java.util.Map;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public class ManagerController {
    private final NewsManagementService newsManagementService = new NewsManagementService();

    public Post createPost(Map<String, Object> data) {
        Post post = new Post();
        post.setTitle((String) data.get(TITLE_ATTRIBUTE));
        post.setContent((String) data.get(CONTENT_ATTRIBUTE));
        post.setAuthor((User) data.get(AUTHOR_ATTRIBUTE));
        post.setDate((LocalDate) data.get(DATE_ATTRIBUTE));

        newsManagementService.addPost(post);
        return post;
    }
}
