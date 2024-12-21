package main.java.kbtu.chill_guys.university_management_system.model.research;

import main.java.kbtu.chill_guys.university_management_system.exception.NotResearcherException;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.permission.CanBeResearcher;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Vector;

public class ResearchProject implements Serializable {

    private String title;
    private String description;
    private Journal journal;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private final Vector<User> participants;
    private final Vector<ResearchPaper> publishedPapers;


    public ResearchProject(String title, String description, Journal journal, LocalDateTime startDate, LocalDateTime endDate, Vector<User> participants, Vector<ResearchPaper> publishedPapers) {
        this.title = title;
        this.description = description;
        this.journal = journal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
        this.publishedPapers = publishedPapers;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<User> getParticipants() {
        return this.participants;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return this.publishedPapers;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void addPublishedPapers(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public void addAllPublishedPapers(Vector<ResearchPaper> papers){
        this.publishedPapers.addAll(papers);
    }

    public void addParticipant(User researcher) {
        if(researcher instanceof Researcher || researcher instanceof CanBeResearcher){
            participants.add(researcher);
        } else {
            throw new NotResearcherException("you cant be participant if you are not researcher");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(journal, that.journal) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(participants, that.participants) && Objects.equals(publishedPapers, that.publishedPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, journal, startDate, endDate, participants, publishedPapers);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", journal=" + journal +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", participants=" + participants +
               ", publishedPapers=" + publishedPapers +
               '}';
    }

    public void joinToProject() {
        //TODO
    }

    public void quitFromProject() {
        //TODO
    }

    public boolean publishPaper() {
        //TODO
        return false;
    }
}
