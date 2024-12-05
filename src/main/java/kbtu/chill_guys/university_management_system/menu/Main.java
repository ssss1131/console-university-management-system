package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.model.Admin;
import main.java.kbtu.chill_guys.university_management_system.model.BaseUser;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Path userFile = Paths.get("users.dat");

        // Создаем репозиторий
        UserRepository userRepository = new UserRepository(userFile);

        // Создаем сервис
        AdminService adminService = new AdminService(userRepository);

        // Пример работы с сервисом
        BaseUser admin = new Admin(UUID.randomUUID(), "admin@kbtu.kz", "Admin", "User", new Vector<>());
        adminService.createUser(admin);

        adminService.removeUser(admin.getId());
    }
}
