package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.TRANSCRIPT_PATH;

public class TranscriptStorage implements Serializable {
    private static final Path PATH = BASE_PATH.resolve(TRANSCRIPT_PATH);
    private static final Logger logger = Logger.getLogger(TranscriptStorage.class.getName());

    private static TranscriptStorage instance;

    private final List<Transcript> transcripts = new ArrayList<>();

    private TranscriptStorage() {
    }

    public static TranscriptStorage getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save transcripts: " + e.getMessage());
            throw new DataPersistenceException("Failed to save transcripts");
        }
    }

    private static TranscriptStorage loadFromFile() {
        if (PATH.toFile().exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                return (TranscriptStorage) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                logger.warning("Failed to load transcripts: " + e.getMessage());
            }
        }
        return new TranscriptStorage();
    }

    public void addTranscript(Transcript transcript) {
        transcripts.add(transcript);
    }

    public List<Transcript> getTranscriptsForStudent(String studentId) {
        return transcripts.stream()
                .filter(t -> t.getStudent().getId().equals(studentId))
                .toList();
    }
}
