package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.GeneralRepository;

import java.util.UUID;

public class AdminService {
    private final GeneralRepository generalRepository;

    public AdminService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public String getLogs() {
        //TODO
        return "";
    }
    
    public void createUser(User user) {
        generalRepository.save(user);
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
