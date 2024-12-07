package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.nio.file.Path;
import java.util.UUID;
import java.util.Vector;

public class UserRepository extends AbstractRepository<User> implements GeneralRepository {
    public UserRepository(Path path) {
        super(path);
    }

    @Override
    public User findById(UUID id) {
        return getAllLines().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findUserByEmail(String email) {
        return getAllLines().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        Vector<User> users = getAllLines();
        users.removeIf(existingUser -> existingUser.getId().equals(user.getId()));
        users.add(user);
        saveData(users);
    }

    @Override
    public void delete(UUID id) {
        Vector<User> users = getAllLines();
        users.removeIf(user -> user.getId().equals(id));
        saveData(users);
    }

    @Override
    public Vector<User> findAll() {
        return getAllLines();
    }
}
