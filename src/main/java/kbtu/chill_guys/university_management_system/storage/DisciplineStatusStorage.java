package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.FILE_PATH;

public class DisciplineStatusStorage implements Serializable {

    private static DisciplineStatusStorage instance;
    private static final Logger  logger = Logger.getLogger(DisciplineStatusStorage.class.getName());

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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save discipline statuses: " + e.getMessage());
            throw new DataPersistenceException("Failed to save discipline statuses");
        }
    }

    private static DisciplineStatusStorage loadFromFile() {
        if (FILE_PATH.toFile().exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH.toFile()))) {
                return (DisciplineStatusStorage) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
               logger.severe("Failed to load discipline statuses: " + e.getMessage());
               throw new DataPersistenceException("Failed to load discipline statuses");
            }
        }
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
    }

    public void removeDiscipline(Discipline discipline) {
        disciplineStatuses.remove(discipline);
    }

    @Override
    public String toString() {
        return "DisciplineStatusStorage{" +
               "disciplineStatuses=" + disciplineStatuses +
               '}';
    }
}


