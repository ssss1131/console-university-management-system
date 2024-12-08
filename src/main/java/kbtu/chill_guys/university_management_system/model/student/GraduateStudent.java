package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.util.Objects;
import java.util.Vector;

public class GraduateStudent extends Student implements Researcher {
    private DiplomaProject project;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public DiplomaProject getProject() {
        return project;
    }

    public void setProject(DiplomaProject project) {
        this.project = project;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }
    
    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }
    
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }
    
    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GraduateStudent that = (GraduateStudent) o;
        return Objects.equals(project, that.project) && Objects.equals(researchProjects, that.researchProjects) && Objects.equals(researchPapers, that.researchPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), project, researchProjects, researchPapers);
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
               "project=" + project +
               ", researchProjects=" + researchProjects +
               ", researchPapers=" + researchPapers +
               "} " + super.toString();
    }
}
