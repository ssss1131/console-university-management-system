package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.Serializable;
import java.util.*;

public class StudentDisciplineStorage implements Serializable {

    private Map<Student, Map<Semester, List<Discipline>>> studentDisciplineHistory = new HashMap<>();



    public void addDiscipline(Student student, Semester semester, Discipline discipline) {
        studentDisciplineHistory
                .computeIfAbsent(student, k -> new HashMap<>())
                .computeIfAbsent(semester, k -> new ArrayList<>())
                .add(discipline);
    }

    public List<Discipline> getDisciplines(Student student, Semester semester) {
        return studentDisciplineHistory
                .getOrDefault(student, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyList());
    }


    public Map<Student, Map<Semester, List<Discipline>>> getStudentDisciplineHistory() {
        return studentDisciplineHistory;
    }

    @Override
    public String toString() {
        return "StudentDisciplineStorage{" +
               "studentDisciplineHistory=" + studentDisciplineHistory +
               '}';
    }
}
