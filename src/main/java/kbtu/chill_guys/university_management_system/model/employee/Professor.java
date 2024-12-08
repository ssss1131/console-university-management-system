package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;


public class Professor extends Teacher implements Researcher {
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

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
        Professor professor = (Professor) o;
        return Objects.equals(researchProjects, professor.researchProjects) && Objects.equals(researchPapers, professor.researchPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), researchProjects, researchPapers);
    }

    @Override
    public String toString() {
        return "Professor{" +
               "researchProjects=" + researchProjects +
               ", researchPapers=" + researchPapers +
               "} " + super.toString();
    }
}
