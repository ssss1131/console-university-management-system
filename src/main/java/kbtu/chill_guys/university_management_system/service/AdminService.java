package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.UserFactory;
import main.java.kbtu.chill_guys.university_management_system.repository.LogRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class AdminService {
    private final UserRepository userRepository = new UserRepository();
    private static final Logger logger = Logger.getLogger(AdminService.class.getName());
    private final LogRepository logRepository = new LogRepository();

    public List<String> getLogs(LogPeriod period) {
        return logRepository.getLogs(period);
    }

    public User createUser(Map<String, Object> data) {
        String email = (String) data.get("email");

        if (isEmailExists(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        UserRole role = (UserRole) data.get("role");
        User user = UserFactory.createUser(role, data);

        userRepository.save(user);
        logger.info("Saved new user: " + user.getEmail());
        return user;
    }


    public void modifyUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(UUID id) {
        userRepository.delete(id);
        logger.info("Removed user with ID: " + id);
    }

    public boolean isExistingUser(UUID id) {
        return userRepository.findById(id) != null;
    }

    public boolean isEmailExists(String email) {
        User existingUser = userRepository.findUserByEmail(email);
        return existingUser != null;
    }
}
