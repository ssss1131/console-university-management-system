package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.UserFactory;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.util.PasswordUtil;

import java.util.Map;
import java.util.UUID;

public class AdminController {
    private final AdminService adminService = new AdminService();

    private AdminService getAdminService() {
        return this.adminService;
    }

    public String getLogs() {
        //TODO
        return "";
    }

    public User createUser(Map<String, Object> data) {
        UserRole role = (UserRole) data.get("role");
        String password = (String) data.get("password");
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        data.put("password", hashedPassword);
        data.put("salt", salt);
        User user = UserFactory.createUser(role, data);
        adminService.createUser(user);
        return user;
    }

    public void modifyUser(Map<String, Object> data) {
        UserRole role = (UserRole) data.get("role");
        User user = UserFactory.createUser(role, data);
        adminService.modifyUser(user);
    }

    public void removeUser(UUID id) {
        adminService.removeUser(id);
    }

    public boolean isExistingUser(UUID id) {
        return adminService.isExistingUser(id);
    }

    public boolean changePasswordToUser() {
        //TODO
        return false;
    }

    public boolean hasUniqueLogin() {
        //TODO
        return false;
    }
}
