package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.util.Objects;
import java.util.Vector;

public class DiplomaProject {
    private String title;
    private String description;
    private ResearchSupervisor supervisor;
    private Vector<ResearchPaper> publishedPapers = new Vector<>();

    public DiplomaProject(String title, String description, ResearchSupervisor supervisor) {
        this.title = title;
        this.description = description;
        this.supervisor = supervisor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResearchSupervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(ResearchSupervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiplomaProject that = (DiplomaProject) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(supervisor, that.supervisor) && Objects.equals(publishedPapers, that.publishedPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, supervisor, publishedPapers);
    }

    @Override
    public String toString() {
        return "DiplomaProject{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", supervisor=" + supervisor +
               ", publishedPapers=" + publishedPapers +
               '}';
    }
}