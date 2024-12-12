package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;

import java.time.LocalDate;
import java.util.Map;

public class ManagerController {
    private final NewsManagementService newsManagementService = new NewsManagementService();

    public Post createPost(Map<String, Object> data) {
        Post post = new Post();
        post.setTitle((String) data.get("title"));
        post.setContent((String) data.get("content"));
        post.setAuthor((User) data.get("author"));
        post.setDate((LocalDate) data.get("date"));

        newsManagementService.addPost(post);
        return post;
    }
}
