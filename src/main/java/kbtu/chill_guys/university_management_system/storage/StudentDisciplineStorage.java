package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.STUDENT_DISCIPLINE_PATH;

public class StudentDisciplineStorage implements Serializable {

    private static final Path PATH = BASE_PATH.resolve(STUDENT_DISCIPLINE_PATH);
    private static final Logger logger = Logger.getLogger(StudentDisciplineStorage.class.getName());

    private static StudentDisciplineStorage instance;

    private final Map<Student, Map<Semester, List<Discipline>>> studentDisciplineHistory = new HashMap<>();

    private StudentDisciplineStorage(){
    }

    public static StudentDisciplineStorage getInstance(){
        if (instance == null){
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save student disciplines: " + e.getMessage());
            throw new DataPersistenceException("Failed to save student disciplines");
        }
    }

    private static StudentDisciplineStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new StudentDisciplineStorage();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                logger.info("Successfully loaded student disciplines from file.");
                return (StudentDisciplineStorage) readObject;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load student disciplines: " + e.getMessage());
                throw new DataPersistenceException("Failed to load student disciplines");
            }
        }

        logger.warning("Discipline of students file not found. Creating a new instance.");
        return new StudentDisciplineStorage();
    }

    public void addDiscipline(Student student, Semester semester, Discipline discipline) {
        studentDisciplineHistory
                .computeIfAbsent(student, k -> new HashMap<>())
                .computeIfAbsent(semester, k -> new ArrayList<>())
                .add(discipline);
        saveToFile();
    }

    public List<Discipline> getDisciplines(Student student, Semester semester) {
        return studentDisciplineHistory
                .getOrDefault(student, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyList());
    }

    public Set<Semester> getSemesters(Student student) {
        return studentDisciplineHistory.getOrDefault(student, Collections.emptyMap()).keySet();
    }

    public void removeDiscipline(Student student, Semester semester, Discipline discipline) {
        Map<Semester, List<Discipline>> semesters = studentDisciplineHistory.get(student);
        if (semesters != null) {
            List<Discipline> disciplines = semesters.get(semester);
            if (disciplines != null) {
                disciplines.remove(discipline);
                if (disciplines.isEmpty()) {
                    semesters.remove(semester);
                }
                if (semesters.isEmpty()) {
                    studentDisciplineHistory.remove(student);
                }
                saveToFile();
            }
        }
    }

    @Override
    public String toString() {
        return "StudentDisciplineStorage{" +
               "studentDisciplineHistory=" + studentDisciplineHistory +
               '}';
    }

    public Map<Semester, List<Discipline>> getAllDisciplines(Student student) {
        return studentDisciplineHistory.get(student);
    }
}
