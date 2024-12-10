package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.UUID;
import java.util.Vector;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.USERS_PATH;

public class UserRepository extends AbstractRepository<User> implements GeneralRepository {

    public UserRepository() {
        super(USERS_PATH);
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
        //TODO надо валидацию добавить что login уникальный
//        Vector<User> users = getAllLines();
//        users.removeIf(existingUser -> existingUser.getId().equals(user.getId())); //TODO UUID всегда уникальный есть ли смысл в этом коде?
//        users.add(user);
        addLine(user);
    }

    @Override
    public void delete(UUID id) {
        Vector<User> users = getAllLines();
        users.removeIf(user -> user.getId().equals(id));
        saveAllLines(users);
    }

    @Override
    public Vector<User> findAll() {
        return getAllLines();
    }
}
