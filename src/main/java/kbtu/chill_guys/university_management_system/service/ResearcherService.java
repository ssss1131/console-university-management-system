package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.repository.ResearchersRepository;

public class ResearcherService {

    private static final ResearcherService instance = new ResearcherService();

    private final ResearchersRepository researchersRepository = ResearchersRepository.getInstance();

    private ResearcherService(){
    }

    public static ResearcherService getInstance(){
        return instance;
    }

    public boolean isResearcher(User loggedUser) {
        return researchersRepository.isResearcher(loggedUser);
    }

    public boolean addResearcher(User researcher){
        if (!isResearcher(researcher)) {
            researchersRepository.addLine(researcher);
            return true;
        }else {
            return false;
        }
    }
}
