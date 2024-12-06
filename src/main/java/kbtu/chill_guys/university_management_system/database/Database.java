package main.java.kbtu.chill_guys.university_management_system.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {
    private static Database instance;
    private final Map<Path, Object> dataMap;

    private Database() {
        this.dataMap = new HashMap<>();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Object getData(Path path) {
        return dataMap.get(path);
    }

    public void saveData(Path path, Object data) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataMap.put(path, data);
    }

    public void loadData(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            Object data = in.readObject();
            dataMap.put(path, data);
        }
    }

    public void removeData(Path path) throws IOException {
        Files.deleteIfExists(path);
        dataMap.remove(path);
    }
}
