package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.exception.DataPersistenceException;
import main.java.kbtu.chill_guys.university_management_system.exception.DisciplineRegistrationException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.BASE_PATH;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.DISCIPLINE_REGISTRATION_PATH;

public class DisciplineRegistrationStorage implements Serializable{

    private static final Path PATH = BASE_PATH.resolve(DISCIPLINE_REGISTRATION_PATH);
    private static final Logger logger = Logger.getLogger(DisciplineRegistrationStorage.class.getName());

    private static DisciplineRegistrationStorage instance;

    private Semester registeringSemester;
    private final Map<Discipline, List<Student>> disciplineStudents = new HashMap<>();
    private final Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> courseDisciplineMapping = new HashMap<>();

    private DisciplineRegistrationStorage() {
    }

    public static DisciplineRegistrationStorage getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            logger.severe("Failed to save student registrations: " + e.getMessage());
            throw new DataPersistenceException("Failed to save student registrations");
        }
    }

    private static DisciplineRegistrationStorage loadFromFile() {
        logger.info("Attempting to load from file: " + PATH.toAbsolutePath());

        if (PATH.toFile().exists()) {
            if (PATH.toFile().length() == 0) {
                logger.warning("File is empty: " + PATH.toAbsolutePath());
                return new DisciplineRegistrationStorage();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.toFile()))) {
                Object readObject = ois.readObject();
                logger.info("Successfully loaded student registrations from file.");
                return (DisciplineRegistrationStorage) readObject;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load student registrations: " + e.getMessage());
                throw new DataPersistenceException("Failed to load student registrations");
            }
        }

        logger.warning("Registrations of students file not found. Creating a new instance.");
        return new DisciplineRegistrationStorage();
    }



    public  Semester getRegisteringSemester() {
        return registeringSemester;
    }

    public void assignDisciplinesToCourseAndProgram(int course, StudentRole role, Program program, List<Discipline> disciplines) {
        courseDisciplineMapping
                .computeIfAbsent(course, k -> new HashMap<>())
                .computeIfAbsent(role, k -> new HashMap<>())
                .compute(program, (key, existingDisciplines) -> {
                    if (existingDisciplines == null) {
                        return new ArrayList<>(disciplines);
                    } else {
                        existingDisciplines.addAll(disciplines);
                        return existingDisciplines;
                    }
                });

        saveToFile();
    }


    public void registerStudentToDiscipline(Discipline discipline, Student student) {
        List<Student> students = disciplineStudents.computeIfAbsent(discipline, k -> new ArrayList<>());
        if (!students.contains(student)) {
            students.add(student);
            saveToFile();
        } else {
            throw new DisciplineRegistrationException("Student is already registered for this discipline.");
        }
    }

    public void unregisterStudentFromDiscipline(Discipline discipline, Student student) {
        List<Student> students = disciplineStudents.get(discipline);
        if (students != null && students.contains(student)) {
            students.remove(student);
            saveToFile();
        } else {
            throw new DisciplineRegistrationException("Student is not registered for this discipline.");
        }
    }

    public List<Student> getStudentsForDiscipline(Discipline discipline) {
        return disciplineStudents.getOrDefault(discipline, Collections.emptyList());
    }

    public void resetForNewSemester(Semester newSemester) {
        registeringSemester = newSemester;
        courseDisciplineMapping.clear();
        disciplineStudents.clear();
        saveToFile();
    }


    public List<Discipline> getAvailableDisciplinesForStudent(int course, StudentRole role, Program program){
        return courseDisciplineMapping
                .getOrDefault(course,Map.of())
                .getOrDefault(role, Map.of())
                .getOrDefault(program, List.of());
    }

    public Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> getCourseDisciplineMapping() {
        return courseDisciplineMapping;
    }

    public Semester closeRegistration(){
        Semester semester = registeringSemester;
        registeringSemester = null;
        courseDisciplineMapping.clear();
        disciplineStudents.clear();
        saveToFile();
        return semester;
    }

    @Override
    public String toString() {
        return "DisciplineRegistrationStorage{" +
               "registeringSemester=" + registeringSemester +
               ", courseDisciplineMapping=" + courseDisciplineMapping +
               ", disciplineStudents=" + disciplineStudents +
               '}';
    }
}
