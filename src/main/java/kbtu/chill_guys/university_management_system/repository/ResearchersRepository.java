package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.User;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.RESEARCHERS_PATH;

public class ResearchersRepository  extends AbstractRepository<User> {


    public ResearchersRepository() {
        super(RESEARCHERS_PATH);
    }
}
