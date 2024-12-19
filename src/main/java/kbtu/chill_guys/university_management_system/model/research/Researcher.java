package main.java.kbtu.chill_guys.university_management_system.model.research;

import java.util.Vector;

public interface Researcher {

    Vector<ResearchPaper> getResearchPapers();
    Vector<ResearchProject> getResearchProjects();

    void addResearchPaper(ResearchProject researchProject);
    void addResearchProjects(ResearchProject researchProject);




}
