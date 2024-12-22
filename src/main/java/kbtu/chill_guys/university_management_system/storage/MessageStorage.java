package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Message;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.MESSAGE_STORAGE_PATH;

public class MessageStorage implements Serializable {

    private static final Path PATH = BASE_PATH.resolve(MESSAGE_STORAGE_PATH);
    private static final Logger logger = Logger.getLogger(MessageStorage.class.getName());

    private static MessageStorage instance;
    private final Map<String, List<Message>> messagesByEmail = new HashMap<>();

    private MessageStorage() {}

    public static MessageStorage getInstance() {
        if(instance == null){
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save message storage: " + e.getMessage());
            throw new DataPersistenceException("Failed to save message storage");
        }
    }

    private static MessageStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new MessageStorage();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                logger.info("Successfully loaded message storage from file.");
                return (MessageStorage) readObject;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load message storage: " + e.getMessage());
                throw new DataPersistenceException("Failed to load message storage");
            }
        }

        logger.warning("message storage with research papers  file not found. Creating a new instance.");
        return new MessageStorage();
    }

    public void sendMessage(Message message) {
        String receiverEmail = message.getRecipient().getEmail();
        messagesByEmail.computeIfAbsent(receiverEmail, k -> new ArrayList<>()).add(message);
        saveToFile();
    }

    public List<Message> getMessages(String receiverEmail) {
        return messagesByEmail.getOrDefault(receiverEmail, new ArrayList<>());
    }

    public void clearMessages(String email) {
        messagesByEmail.remove(email);
        saveToFile();
    }
}