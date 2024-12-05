package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import java.util.UUID;
import java.util.Vector;

public interface GeneralRepository {
    User findById(UUID id);
    void save(User user);
    void delete(UUID id);
    Vector<User> findAll();
}
