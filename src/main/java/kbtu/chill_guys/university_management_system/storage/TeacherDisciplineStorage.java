package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.TEACHER_DISCIPLINE_PATH;

public class TeacherDisciplineStorage implements Serializable {
    private static final Path PATH = BASE_PATH.resolve(TEACHER_DISCIPLINE_PATH);
    private static final Logger logger = Logger.getLogger(TeacherDisciplineStorage.class.getName());

    private static TeacherDisciplineStorage instance;

    private final Map<Teacher, Map<Semester, Map<Discipline, Map<Student, List<LessonRecord>>>>> teacherLessonRecords = new HashMap<>();

    private TeacherDisciplineStorage() {
    }

    public static TeacherDisciplineStorage getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save teacher lesson records: " + e.getMessage());
            throw new DataPersistenceException("Failed to save teacher lesson records");
        }
    }

    private static TeacherDisciplineStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new TeacherDisciplineStorage(); // Пустой экземпляр, если файл пуст
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                if (readObject instanceof TeacherDisciplineStorage) {
                    logger.info("Successfully loaded teacher lesson records from file.");
                    return (TeacherDisciplineStorage) readObject;
                } else {
                    logger.warning("Unexpected data in file. Creating a new storage instance.");
                }
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load teacher lesson records: " + e.getMessage());
            }
        } else {
            logger.warning("File not found: " + PATH.toAbsolutePath());
        }

        return new TeacherDisciplineStorage();
    }


    public void addLessonRecord(Teacher teacher, Semester semester, Discipline discipline, Student student, LessonRecord record) {
        teacherLessonRecords
                .computeIfAbsent(teacher, t -> new HashMap<>())
                .computeIfAbsent(semester, s -> new HashMap<>())
                .computeIfAbsent(discipline, d -> new HashMap<>())
                .computeIfAbsent(student, st -> new ArrayList<>())
                .add(record);
        saveToFile();
    }

    public List<Discipline> getDisciplines(Teacher teacher, Semester semester) {
        return new ArrayList<>(teacherLessonRecords
                .getOrDefault(teacher, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyMap())
                .keySet());
    }

    public void assignDisciplineToTeacher(Teacher teacher, Semester semester, Discipline discipline) {
        teacherLessonRecords
                .computeIfAbsent(teacher, t -> new HashMap<>())
                .computeIfAbsent(semester, s -> new HashMap<>())
                .putIfAbsent(discipline, new HashMap<>());
        saveToFile();
    }

    public void registerStudentToTeacherDiscipline(Teacher teacher, Semester semester, Discipline discipline, Student student) {
        teacherLessonRecords
                .computeIfAbsent(teacher, t -> new HashMap<>())
                .computeIfAbsent(semester, s -> new HashMap<>())
                .computeIfAbsent(discipline, d -> new HashMap<>())
                .computeIfAbsent(student, st -> new ArrayList<>());
        saveToFile();
    }

    public Teacher getTeacherForDiscipline(Semester semester, Discipline discipline) {
        for (var entry : teacherLessonRecords.entrySet()) {
            Teacher teacher = entry.getKey();
            Map<Semester, Map<Discipline, Map<Student, List<LessonRecord>>>> semesterMap = entry.getValue();
            if (semesterMap.containsKey(semester) && semesterMap.get(semester).containsKey(discipline)) {
                return teacher;
            }
        }
        return null;
    }

    public List<Student> getStudentsInDiscipline(Teacher teacher, Semester semester, Discipline discipline) {
        return new ArrayList<>(teacherLessonRecords
                .getOrDefault(teacher, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyMap())
                .getOrDefault(discipline, Collections.emptyMap())
                .keySet());
    }

    public Map<Student, List<LessonRecord>> getLessonRecordsForDiscipline(Semester semester, Discipline discipline) {
        for (Map.Entry<Teacher, Map<Semester, Map<Discipline, Map<Student, List<LessonRecord>>>>> teacherEntry : teacherLessonRecords.entrySet()) {
            Map<Semester, Map<Discipline, Map<Student, List<LessonRecord>>>> semesterMap = teacherEntry.getValue();
            if (semesterMap.containsKey(semester) && semesterMap.get(semester).containsKey(discipline)) {
                return semesterMap.get(semester).get(discipline);
            }
        }
        return Map.of();
    }

    public List<LessonRecord> getLessonRecordsForStudent(Teacher teacher, Semester semester, Discipline discipline, Student student) {
        return teacherLessonRecords
                .getOrDefault(teacher, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyMap())
                .getOrDefault(discipline, Collections.emptyMap())
                .getOrDefault(student, Collections.emptyList());
    }
}
