package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Journal;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Vector;

public class ResearchPaper {

    private final UUID ID;
    private String title;
    private String thesis;
    private Journal journal;
    private Integer citations;
    private final Vector<Researcher> authors = new Vector<>();
    private String doi;
    private LocalDate publicationDate;

    {
        ID = UUID.randomUUID();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return this.thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public Journal getJournal() {
        return this.journal;
    }

    public void setJournal(Journal journal) {
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

    public void addAuthor(Researcher researcher){
        authors.add(researcher);
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
