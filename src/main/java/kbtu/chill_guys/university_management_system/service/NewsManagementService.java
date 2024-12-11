package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.repository.PostRepository;

public class NewsManagementService {
    private final PostRepository postRepository = new PostRepository();

    public void addPost(Post post) {
        postRepository.savePost(post);
    }
}
