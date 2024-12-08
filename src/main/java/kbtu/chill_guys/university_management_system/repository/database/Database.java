package main.java.kbtu.chill_guys.university_management_system.repository.database;

import java.io.*;
import java.nio.file.*;
import java.util.Vector;

public final class Database {
    private static final Database INSTANCE = new Database();
    private final Path basePath = Paths.get(System.getProperty("user.dir"), "resources", "db");


    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Vector<String> readFile(Path path) {
        Vector<String> lines = new Vector<>();
        try (BufferedReader br = Files.newBufferedReader(basePath.resolve(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + basePath.resolve(path)); // TODO Logger
            e.printStackTrace();
        }
        return lines;
    }

    public void writeFile(Path path, Vector<String> data) {
        try (BufferedWriter bw = Files.newBufferedWriter(basePath.resolve(path))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + basePath.resolve(path)); // TODO Logger
            e.printStackTrace();
        }
    }

    public void appendToFile(Path path, String data) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                basePath.resolve(path), StandardOpenOption.APPEND)) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error appending to file: " + basePath.resolve(path)); // TODO Logger
            e.printStackTrace();
        }
    }
}
