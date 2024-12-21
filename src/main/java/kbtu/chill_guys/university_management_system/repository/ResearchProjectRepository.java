package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public class ResearchProjectRepository extends AbstractRepository<ResearchProject> {

    private static final ResearchProjectRepository instance = new ResearchProjectRepository();

    public ResearchProjectRepository() {
        super(RESEARCH_PROJECT_PATH);
    }

    public static ResearchProjectRepository getInstance() {
        return instance;
    }
}
