package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.UserFactory;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;

import java.util.Map;
import java.util.UUID;

public class AdminController {
    private AdminService adminService;

    private AdminService getAdminService() {
        return this.adminService;
    }

    private void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public String getLogs() {
        //TODO
        return "";
    }

    public void createUser(Map<String, Object> data) {
        String type = (String) data.get("type");
        BaseUser user = UserFactory.createUser(type, data);
        adminService.createUser(user);
    }

    public void modifyUser(Map<String, Object> data) {
        String type = (String) data.get("type");
        BaseUser user = UserFactory.createUser(type, data);
        adminService.modifyUser(user);
    }

    public void removeUser(Map<String, Object> data) {
        UUID id = (UUID) data.get("id");
        adminService.removeUser(id);
    }

    public boolean isExistingUser() {
        //TODO
        return false;
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
