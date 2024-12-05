package main.java.kbtu.chill_guys.university_management_system.model.research;

import universityManagementSystem.models.student.research.Researcher;

import java.time.LocalDate;
import java.util.Vector;

public class ResearchPaper {
    private String title;
    private String tesis;
    private String journal;
    private Integer citations;
    private Vector<Researcher> authors;
    private String doi;
    private LocalDate publicationDate;

    private String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private String getAbstract() {
        return this.tesis;
    }

    private void setTesis(String tesis) {
        this.tesis = tesis;
    }

    private String getJournal() {
        return this.journal;
    }

    private void setJournal(String journal) {
        this.journal = journal;
    }

    private Integer getCitations() {
        return this.citations;
    }

    private void setCitations(Integer citations) {
        this.citations = citations;
    }

    private Vector<Researcher> getAuthors() {
        return this.authors;
    }

    private void setAuthors(Vector<Researcher> authors) {
        this.authors = authors;
    }

    private String getDoi() {
        return this.doi;
    }

    private void setDoi(String doi) {
        this.doi = doi;
    }

    private LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    private void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCitation() {
        //TODO
        return "";
    }
    public void addAuthor() {
        //TODO
    }
}
