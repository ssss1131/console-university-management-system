package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.RESEARCH_PAPER_PATH;

public class ResearchPaperRepository extends AbstractRepository<ResearchPaper> {

    private static final ResearchPaperRepository instance = new ResearchPaperRepository();

    public static ResearchPaperRepository getInstance() {
        return instance;
    }

    private ResearchPaperRepository() {
        super(RESEARCH_PAPER_PATH);
    }
}
