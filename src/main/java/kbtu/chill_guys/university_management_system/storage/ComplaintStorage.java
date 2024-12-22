package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Complaint;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.COMPLAINT_PATH;

public class ComplaintStorage implements Serializable {
    private static final Path PATH = BASE_PATH.resolve(COMPLAINT_PATH);
    private static ComplaintStorage instance;
    private static final Logger logger = Logger.getLogger(ComplaintStorage.class.getName());

    private final List<Complaint> complaints = new ArrayList<>();

    private ComplaintStorage() {
    }

    public static ComplaintStorage getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    private static ComplaintStorage loadFromFile() {
        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.severe("File is empty. Creating a new ComplaintStorage instance.");
                return new ComplaintStorage();
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                return (ComplaintStorage) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Error loading complaints from file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.severe("Complaint file not found. Creating a new instance.");
        }
        return new ComplaintStorage();
    }

    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        saveToFile();
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public Complaint getComplaintById(UUID id) {
        return complaints.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
