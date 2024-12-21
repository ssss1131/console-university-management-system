package main.java.kbtu.chill_guys.university_management_system.model.research;

import java.util.Vector;

public interface Researcher {

    Vector<ResearchPaper> getResearchPapers();
    Vector<ResearchProject> getResearchProjects();

    void addResearchPaper(ResearchProject researchProject);
    void addResearchProjects(ResearchProject researchProject);

    default int calculateHIndex(){
        Vector<ResearchPaper> papers = getResearchPapers();
        papers.sort((p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));

        int hIndex = 0;

        for (int i = 0; i < papers.size(); i++) {
            if (papers.get(i).getCitations() >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }

        return hIndex;
    };


}
