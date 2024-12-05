package universityManagementSystem.models.research;

import universityManagementSystem.models.student.research.Researcher;
import java.util.Vector;

public class ResearchProject {
    private String topic;
    private Vector<Researcher> participants;
    private Vector<ResearchPaper> publishedPapers;
    
    private String getTopic() {
        return this.topic;
    }

    private void setTopic(String topic) {
        this.topic = topic;
    }

    private Vector<Researcher> getParticipants() {
        return this.participants;
    }

    private void setParticipants(Vector<Researcher> participants) {
        this.participants = participants;
    }

    private Vector<ResearchPaper> getPublishedPapers() {
        return this.publishedPapers;
    }

    private void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
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
