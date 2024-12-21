package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.UserFactory;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.util.PasswordUtil;

import java.util.*;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public class AdminController {
    private final AdminService adminService = new AdminService();

    public List<String> getLogs(LogPeriod period) {
        return adminService.getLogs(period);
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
