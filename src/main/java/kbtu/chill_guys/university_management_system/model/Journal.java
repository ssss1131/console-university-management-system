package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

public class Journal implements Serializable {
    private String name;
    private UUID id;
    private Vector<User> subscribers;
    private Vector<ResearchPaper> researchPapers;
    private Vector<ResearchProject> researchProjects;

    public Journal() {}

    public Journal(String name, UUID id) {
        this.name = name;
        this.id = id != null ? id : UUID.randomUUID();
        this.subscribers = new Vector<>();
        this.researchPapers = new Vector<>();
        this.researchProjects = new Vector<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public Vector<User> getSubscribers() {
        return subscribers;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSubscribers(Vector<User> subscribers) {
        this.subscribers = subscribers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public boolean addSubscriber(User subscriber) {
        if (subscriber != null && !subscribers.contains(subscriber)) {
            return subscribers.add(subscriber);
        }
        return false;
    }

    public boolean removeSubscriber(User subscriber) {
        return subscribers.remove(subscriber);
    }

    public void publish(ResearchPaper post) {
        if (post != null) {
            researchPapers.add(post);
            for (Subscriber subscriber : subscribers) {
                subscriber.update(post);
            }
        }
    }

    public void addPost(ResearchPaper paper){
        researchPapers.add(paper);
    }

    public void addProject(ResearchProject project){
        researchProjects.add(project);
    }
}
