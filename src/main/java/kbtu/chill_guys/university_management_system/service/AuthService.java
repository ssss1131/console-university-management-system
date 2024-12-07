package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.util.PasswordUtil;

import java.util.Map;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public boolean authenticate(String email, String plainPassword) {
        User userData = userRepository.findUserByEmail(email);

        if (userData == null) {
            return false;
        }

        String salt = userData.getSalt();
        String hashedPassword = userData.getPassword();

        return PasswordUtil.checkPassword(plainPassword, salt, hashedPassword);
    }
}
