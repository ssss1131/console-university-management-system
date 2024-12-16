package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.UserFactory;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.util.Constant;
import main.java.kbtu.chill_guys.university_management_system.util.PasswordUtil;

import java.util.*;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public class AdminController {
    private final AdminService adminService = new AdminService();

    public List<String> getLogs(LogPeriod period) {
        return adminService.getLogs(period);
    }

    public User createUser(Map<String, Object> data) {
        UserRole role = (UserRole) data.get(USER_ROLE_ATTRIBUTE);
        String password = (String) data.get(PASSWORD_ATTRIBUTE);
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        data.put(PASSWORD_ATTRIBUTE, hashedPassword);
        data.put(SALT_ATTRIBUTE, salt);
        User user = UserFactory.createUser(role, data);
        adminService.createUser(user);
        return user;
    }

    public void modifyUser(Map<String, Object> data) {
        UserRole role = (UserRole) data.get(USER_ROLE_ATTRIBUTE);
        User user = UserFactory.createUser(role, data);
        adminService.modifyUser(user);
    }

    public void removeUser(UUID id) {
        adminService.removeUser(id);
    }

    public boolean isExistingUser(UUID id) {
        return adminService.isExistingUser(id);
    }
}
