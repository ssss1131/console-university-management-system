package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;

public class ManagerController {
    private final NewsManagementService newsManagementService;

    public ManagerController(NewsManagementService newsManagementService) {
        this.newsManagementService = newsManagementService;
    }

    public void addNews(Post post) {
        newsManagementService.addPost(post);
    }
}
