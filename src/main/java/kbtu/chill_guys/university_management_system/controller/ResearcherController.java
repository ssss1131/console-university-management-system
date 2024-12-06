package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.service.ResearchService;

import java.util.Vector;

public class ResearcherController {
    private ResearchService researchService;

    public ResearchService getResearchService() {
        return this.researchService;
    }
    public void setResearchService(ResearchService researchService) {
        this.researchService = researchService;
    }

    public Vector<ResearchProject> getProjects() {
        //TODO
        return null;
    }

    public Vector<ResearchPaper> getPapers() {
        //TODO
        return null;
    }
}
