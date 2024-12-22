package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.JOURNAL_STORAGE_PATH;

public class JournalStorage implements Serializable {

    private static final Path PATH = BASE_PATH.resolve(JOURNAL_STORAGE_PATH);
    private static final Logger logger = Logger.getLogger(JournalStorage.class.getName());

    private static JournalStorage instance;

    private final Map<Journal, List<ResearchPaper>> papersByJournal = new HashMap<>();
    private final Map<Journal, List<ResearchProject>> projectsByJournal = new HashMap<>();


    public static JournalStorage getInstance() {
        if(instance == null){
            instance = loadFromFile();
        }
        return instance;
    }

    private JournalStorage(){
    }


    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save journal storage: " + e.getMessage());
            throw new DataPersistenceException("Failed to save journal storage");
        }
    }

    private static JournalStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new JournalStorage();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                logger.info("Successfully loaded journal storage from file.");
                return (JournalStorage) readObject;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load journal storage: " + e.getMessage());
                throw new DataPersistenceException("Failed to load journal storage");
            }
        }

        logger.warning("journal storage with research papers  file not found. Creating a new instance.");
        return new JournalStorage();
    }

    public void addNewJournal(Journal journal) {
        papersByJournal.put(journal, new ArrayList<>());
        projectsByJournal.put(journal, new ArrayList<>());
        saveToFile();
    }

    public void addResearchProject(Journal journal, ResearchProject researchProject){
        List<ResearchProject> researchProjects = projectsByJournal.get(journal);
        researchProjects.add(researchProject);
        saveToFile();
    }

    public void addResearchPaper(Journal journal, ResearchPaper researchPaper) {
        List<ResearchPaper> researchPapers = papersByJournal.get(journal);
        researchPapers.add(researchPaper);
        saveToFile();
    }

    public Map<Journal, List<ResearchPaper>> getPapersByJournal() {
        return papersByJournal;
    }

    public Map<Journal, List<ResearchProject>> getProjectsByJournal() {
        return projectsByJournal;
    }

    public void delete(Journal journal) {
        papersByJournal.remove(journal);
        projectsByJournal.remove(journal);
        saveToFile();
    }

    public boolean isNewJournal(Journal journal) {
        return !(papersByJournal.containsKey(journal) || projectsByJournal.containsKey(journal));
    }

    public List<ResearchPaper> getPapers(Journal journal){
        return papersByJournal.get(journal);
    }

    public List<ResearchProject> getProjects(Journal journal){
        return projectsByJournal.get(journal);
    }

    public Set<Journal> getJournals() {
        return projectsByJournal.keySet();
    }
}
