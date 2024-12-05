package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return this.tesis;
    }

    public void setTesis(String tesis) {
        this.tesis = tesis;
    }

    public String getJournal() {
        return this.journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public Integer getCitations() {
        return this.citations;
    }

    public void setCitations(Integer citations) {
        this.citations = citations;
    }

    public Vector<Researcher> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Vector<Researcher> authors) {
        this.authors = authors;
    }

    public String getDoi() {
        return this.doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
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
