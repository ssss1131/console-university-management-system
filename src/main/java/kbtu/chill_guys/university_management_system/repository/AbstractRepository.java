package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.repository.database.Database;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

public abstract class AbstractRepository<T> {
    private Database database;
    private final Path path;

    public AbstractRepository(Path path) {
        this.database = Database.getInstance();
        this.path = path;
    }

    public Vector<T> getAllLines() {
        try {
            return database.loadData(path);
        } catch (IOException | ClassNotFoundException e) {
            return new Vector<>();
        }
    }

    public void saveAllLines(Vector<T> data) {
        database.saveData(path, data);
    }

    public void addLine(T line) {
        Vector<T> data = getAllLines();
        data.add(line);
        saveAllLines(data);
    }

    public void removeLine(T line) {
        Vector<T> data = getAllLines();
        data.remove(line);
        saveAllLines(data);
    }

    public void updateLine(T oldLine, T newLine) {
        Vector<T> data = getAllLines();
        int index = data.indexOf(oldLine);
        if (index != -1) {
            data.set(index, newLine);
            saveAllLines(data);
        }
    }

    public void update(T line) {
        Vector<T> data = getAllLines();
        int index = data.indexOf(line);
        if (index != -1) {
            data.set(index, line);
        } else {
            data.add(line);
        }
        saveAllLines(data);
    }

}
