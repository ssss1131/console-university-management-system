package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.GeneralRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;

import java.util.UUID;
import java.util.logging.Logger;

public class AdminService {
    private final GeneralRepository generalRepository = new UserRepository();
    private static final Logger logger = Logger.getLogger(AdminService.class.getName());

    public AdminService() {
    }

    public String getLogs() {
        //TODO
        return "";
    }
    
    public void createUser(User user) {
        generalRepository.save(user);
        logger.info("Saved new user: " + user.getEmail());
    }

    public void modifyUser(User user) {
        generalRepository.save(user);
    }
    
    public void removeUser(UUID id) {
        generalRepository.delete(id);
    }

    public boolean isExistingUser(UUID id) {
        return generalRepository.findById(id) != null;
    }
}
