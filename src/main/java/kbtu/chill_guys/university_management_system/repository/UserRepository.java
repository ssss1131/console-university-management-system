package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.USERS_PATH;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(USERS_PATH);
    }

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

    public void save(User user) {
        Vector<User> users = getAllLines();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                saveAllLines(users);
                return;
            }
        }

        users.add(user);
        saveAllLines(users);
    }

    public void delete(UUID id) {
        Vector<User> users = getAllLines();
        users.removeIf(user -> user.getId().equals(id));
        saveAllLines(users);
    }

    public Vector<User> findAll() {
        return getAllLines();
    }

    public List<User> findUsersByRole(UserRole role) {
        return getAllLines().stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
    }
}
