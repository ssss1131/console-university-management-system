package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.GeneralRepository;

import main.java.kbtu.chill_guys.university_management_system.repository.LogRepository;

import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;


import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


public class AdminService {
    private final GeneralRepository generalRepository = new UserRepository();
    private static final Logger logger = Logger.getLogger(AdminService.class.getName());
    private final LogRepository logRepository = new LogRepository();

    public List<String> getLogs(LogPeriod period) {
        return logRepository.getLogs(period);
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
