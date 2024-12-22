package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

/**
 * The {@code Journal} class represents a research journal in the university management system.
 * Journals can have subscribers, publish research papers, and manage associated research projects.
 */
public class Journal implements Serializable {

    /**
     * The name of the journal.
     */
    private String name;

    /**
     * The unique identifier of the journal.
     */
    private UUID id;

    /**
     * A collection of users subscribed to the journal.
     */
    private Vector<User> subscribers;

    /**
     * A collection of research papers published in the journal.
     */
    private Vector<ResearchPaper> researchPapers;

    /**
     * A collection of research projects associated with the journal.
     */
    private Vector<ResearchProject> researchProjects;

    /**
     * Default constructor for the {@code Journal} class.
     */
    public Journal() {}

    /**
     * Constructs a new {@code Journal} with the specified name and identifier.
     *
     * @param name the name of the journal
     * @param id   the unique identifier of the journal (if {@code null}, a new UUID will be generated)
     */
    public Journal(String name, UUID id) {
        this.name = name;
        this.id = id != null ? id : UUID.randomUUID();
        this.subscribers = new Vector<>();
        this.researchPapers = new Vector<>();
        this.researchProjects = new Vector<>();
    }

    /**
     * Gets the name of the journal.
     *
     * @return the name of the journal
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique identifier of the journal.
     *
     * @return the UUID of the journal
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the collection of subscribers to the journal.
     *
     * @return the subscribers of the journal
     */
    public Vector<User> getSubscribers() {
        return subscribers;
    }

    /**
     * Gets the collection of research papers published in the journal.
     *
     * @return the research papers
     */
    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    /**
     * Sets the name of the journal.
     *
     * @param name the new name of the journal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the unique identifier of the journal.
     *
     * @param id the new UUID for the journal
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the subscribers of the journal.
     *
     * @param subscribers the new collection of subscribers
     */
    public void setSubscribers(Vector<User> subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * Sets the research papers published in the journal.
     *
     * @param researchPapers the new collection of research papers
     */
    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    /**
     * Adds a subscriber to the journal.
     *
     * @param subscriber the user to subscribe
     * @return {@code true} if the subscriber was added, {@code false} otherwise
     */
    public boolean addSubscriber(User subscriber) {
        if (subscriber != null && !subscribers.contains(subscriber)) {
            return subscribers.add(subscriber);
        }
        return false;
    }

    /**
     * Removes a subscriber from the journal.
     *
     * @param subscriber the user to remove
     * @return {@code true} if the subscriber was removed, {@code false} otherwise
     */
    public boolean removeSubscriber(User subscriber) {
        return subscribers.remove(subscriber);
    }

    /**
     * Publishes a research paper to the journal and notifies all subscribers.
     *
     * @param post the research paper to publish
     */
    public void publish(ResearchPaper post) {
        if (post != null) {
            researchPapers.add(post);
            for (Subscriber subscriber : subscribers) {
                subscriber.update(post);
            }
        }
    }

    /**
     * Adds a research paper to the journal without notifying subscribers.
     *
     * @param paper the research paper to add
     */
    public void addPost(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    /**
     * Adds a research project to the journal.
     *
     * @param project the research project to add
     */
    public void addProject(ResearchProject project) {
        researchProjects.add(project);
    }
}
