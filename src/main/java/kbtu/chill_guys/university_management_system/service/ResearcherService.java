package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.ResearchersRepository;

public class ResearcherService {

    private final ResearchersRepository researchersRepository = new ResearchersRepository();

    public static boolean isResearcher(User loggedUser) {
        return false;
    }
}
