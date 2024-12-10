package main.java.kbtu.chill_guys.university_management_system.repository.database;

import java.io.*;
import java.nio.file.*;
import java.util.logging.Logger;

public final class Database {

    private static final Logger logger = Logger.getLogger(Database.class.getName());

    private static final Database INSTANCE = new Database();
    private final Path basePath = Paths.get(System.getProperty("user.dir"), "src","main", "resources", "db");


    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }

//    public Vector<String> readFile(Path path) {
//        Vector<String> lines = new Vector<>();
//        try (BufferedReader br = Files.newBufferedReader(basePath.resolve(path))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                lines.add(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + basePath.resolve(path)); // TODO Logger
//            e.printStackTrace();
//        }
//        return lines;
//    }
//
//    public void writeFile(Path path, Vector<String> data) {
//        try (BufferedWriter bw = Files.newBufferedWriter(basePath.resolve(path))) {
//            for (String line : data) {
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            System.err.println("Error writing to file: " + basePath.resolve(path)); // TODO Logger
//            e.printStackTrace();
//        }
//    }
//
//    public void appendToFile(Path path, String data) {
//        try (BufferedWriter bw = Files.newBufferedWriter(
//                basePath.resolve(path), StandardOpenOption.APPEND)) {
//            bw.write(data);
//            bw.newLine();
//        } catch (IOException e) {
//            System.err.println("Error appending to file: " + basePath.resolve(path)); // TODO Logger
//            e.printStackTrace();
//        }
//    }

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
