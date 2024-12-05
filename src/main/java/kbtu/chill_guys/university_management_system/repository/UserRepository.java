package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;
import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.nio.file.Path;
import java.util.UUID;
import java.util.Vector;

public class UserRepository extends AbstractRepository<BaseUser> implements GeneralRepository {
    public UserRepository(Path path) {
        super(path);
    }

    @Override
    public BaseUser findById(UUID id) {
        return getAllLines().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(BaseUser user) {
        Vector<BaseUser> users = getAllLines();
        users.removeIf(existingUser -> existingUser.getId().equals(user.getId()));
        users.add(user);
        saveData(users);
    }

    @Override
    public void delete(UUID id) {
        Vector<BaseUser> users = getAllLines();
        users.removeIf(user -> user.getId().equals(id));
        saveData(users);
    }

    @Override
    public Vector<BaseUser> findAll() {
        return getAllLines();
    }
}
