package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.DISCIPLINE_STATUS_PATH;

public class DisciplineStatusStorage implements Serializable {

    private static final Path PATH = BASE_PATH.resolve(DISCIPLINE_STATUS_PATH);
    private static final Logger logger = Logger.getLogger(DisciplineStatusStorage.class.getName());

    private static DisciplineStatusStorage instance;

    private final Map<Discipline, Status> disciplineStatuses = new HashMap<>();

    private DisciplineStatusStorage() {
    }

    public static DisciplineStatusStorage getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save discipline statuses: " + e.getMessage());
            throw new DataPersistenceException("Failed to save discipline statuses");
        }
    }

    private static DisciplineStatusStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new DisciplineStatusStorage();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                logger.info("Successfully loaded discipline statuses from file.");
                return (DisciplineStatusStorage) readObject;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load discipline statuses: " + e.getMessage());
                throw new DataPersistenceException("Failed to load discipline statuses");
            }
        }

        logger.warning("Discipline status file not found. Creating a new instance.");
        return new DisciplineStatusStorage();
    }


    public Map<Discipline, Status> getAllStatuses() {
        return disciplineStatuses;
    }

    public Status getStatus(Discipline discipline) {
        return disciplineStatuses.get(discipline);
    }

    public void setStatus(Discipline discipline, Status status) {
        disciplineStatuses.put(discipline, status);
        saveToFile();
    }

    public void removeDiscipline(Discipline discipline) {
        disciplineStatuses.remove(discipline);
        saveToFile();
    }

    public Vector<Discipline> getDisciplinesByStatus(Status status) {
        return disciplineStatuses.entrySet().stream()
                .filter(entry -> entry.getValue() == status)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(Vector::new));
    }


    @Override
    public String toString() {
        return "DisciplineStatusStorage{" +
               "disciplineStatuses=" + disciplineStatuses +
               '}';
    }
}


