package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Journal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public class ResearchPaper implements Serializable {

    private String title;
    private String thesis;
    private Journal journal;
    private Integer citations;
    private final Vector<Researcher> authors = new Vector<>();
    private String doi;
    private LocalDate publicationDate;


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThesis() {
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

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(doi);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
               "title='" + title + '\'' +
               ", thesis='" + thesis + '\'' +
               ", journal=" + journal +
               ", citations=" + citations +
               ", authors=" + authors +
               ", doi='" + doi + '\'' +
               ", publicationDate=" + publicationDate +
               '}';
    }
}
