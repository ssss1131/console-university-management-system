package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.exception.DisciplineOverflowException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.*;

public class DisciplineRegistrationStorage {

    private final Map<Discipline, List<Student>> disciplineStudents = new HashMap<>();
    private final Map<Discipline, Integer> disciplineCapacity = new HashMap<>();

    public void registerStudent(Discipline discipline, Student student) {
        int maxCapacity = disciplineCapacity.getOrDefault(discipline, Integer.MAX_VALUE);
        List<Student> students = disciplineStudents.computeIfAbsent(discipline, k -> new ArrayList<>());

        if (students.size() < maxCapacity) {
            students.add(student);
        } else {
            throw new DisciplineOverflowException("Discipline " + discipline.getName() + " has reached its maximum capacity.");
        }
    }

    public void unregisterStudent(Discipline discipline, Student student) {
        List<Student> students = disciplineStudents.get(discipline);
        if (students != null) {
            students.remove(student);
        }
    }

    public void setDisciplineCapacity(Discipline discipline, int maxCapacity) {
        disciplineCapacity.put(discipline, maxCapacity);
    }

    public List<Student> getStudents(Discipline discipline) {
        return disciplineStudents.getOrDefault(discipline, Collections.emptyList());
    }

    public int getDisciplineCapacity(Discipline discipline) {
        return disciplineCapacity.getOrDefault(discipline, Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        return "DisciplineStudentStorage{" +
               "disciplineStudents=" + disciplineStudents +
               ", disciplineCapacity=" + disciplineCapacity +
               '}';
    }
}
