package universityManagementSystem.models.research;

import universityManagementSystem.models.BaseUser;
import universityManagementSystem.permissions.CanBeResearcher;

import java.util.Vector;

public class ResearcherDecorator <T extends BaseUser> implements CanBeResearcher {
    private T User;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    private T getUser() {
        return this.User;
    }

    private void setUser(T User) {
        this.User = User;
    }

    private Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    private void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    private Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    private void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
}
