package main.java.kbtu.chill_guys.university_management_system.model.research;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Vector;

public class ResearchProject {

    private final UUID ID;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private final Vector<Researcher> participants = new Vector<>();
    private final Vector<ResearchPaper> publishedPapers = new Vector<>();

    {
        ID = UUID.randomUUID();
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

    public Vector<Researcher> getParticipants() {
        return this.participants;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return this.publishedPapers;
    }

    public void addPublishedPapers(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public void addAllPublishedPapers(Vector<ResearchPaper> papers){
        this.publishedPapers.addAll(papers);
    }

    public void addParticipant(Researcher researcher) {
        participants.add(researcher);
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
