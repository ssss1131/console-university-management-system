package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.nio.file.Path;
import java.util.Vector;

public class PostRepository extends AbstractRepository<Post> {
    public PostRepository(Path path) {
        super(path);
    }

    public Vector<Post> getAllPosts() {
        return getAllLines();
    }

    public void savePost(Post post) {
        addLine(post);
    }

    public void deletePost(Post post) {
        removeLine(post);
    }

    public void updatePost(Post oldPost, Post newPost) {
        updateLine(oldPost, newPost);
    }
}
