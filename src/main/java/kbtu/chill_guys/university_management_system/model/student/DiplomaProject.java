package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.exception.InvalidHIndexException;
import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.util.Constant;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.MIN_H_INDEX;

public class DiplomaProject implements Serializable {
    private String title;
    private String description;
    private ResearchSupervisor supervisor;
    private Vector<ResearchPaper> publishedPapers = new Vector<>();

    public DiplomaProject() {
    }

    public DiplomaProject(String title, String description) {
        this.title = title;
        this.description = description;
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
        if(supervisor.calculateHIndex() < MIN_H_INDEX){
            throw new InvalidHIndexException("H index of supervisor is less than required");
        }
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