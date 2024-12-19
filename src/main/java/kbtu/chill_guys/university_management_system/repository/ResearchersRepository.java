package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.RESEARCHERS_PATH;

public class ResearchersRepository  extends AbstractRepository<User> {

    private static final ResearchersRepository instance = new ResearchersRepository();

    private ResearchersRepository(){
        super(RESEARCHERS_PATH);
    }

    public static ResearchersRepository getInstance(){
        return instance;
    }

    public boolean isResearcher(User loggedUser) {
        return getAllLines().stream()
                .anyMatch(user -> user.equals(loggedUser));
    }
}
