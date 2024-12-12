package main.java.kbtu.chill_guys.university_management_system.repository.database;

import java.io.*;
import java.nio.file.*;
import java.util.logging.Logger;

public final class Database {

    private static final Logger logger = Logger.getLogger(Database.class.getName());

    private static final Database INSTANCE = new Database();
    private final Path basePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "db");


    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }


    public void saveData(Path path, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(basePath.resolve(path)))) {
            oos.writeObject(data);
        } catch (IOException e) {
            logger.warning("Error saving data, cause path incorrect: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T loadData(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(basePath.resolve(path)))) {
            return (T) ois.readObject();
        }
    }
}
