package main.java.kbtu.chill_guys.university_management_system.model.research;

import java.util.Vector;

public class ResearchProject {
    private String topic;
    private Vector<Researcher> participants;
    private Vector<ResearchPaper> publishedPapers;
    
    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Vector<Researcher> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Vector<Researcher> participants) {
        this.participants = participants;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return this.publishedPapers;
    }

    public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
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
