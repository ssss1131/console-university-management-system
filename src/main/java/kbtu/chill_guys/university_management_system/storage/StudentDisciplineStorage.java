package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.Serializable;
import java.util.*;

public class StudentDisciplineStorage implements Serializable {

    private final Map<Student, Map<Semester, List<Discipline>>> studentDisciplineHistory = new HashMap<>();



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
            }
        }
    }

    @Override
    public String toString() {
        return "StudentDisciplineStorage{" +
               "studentDisciplineHistory=" + studentDisciplineHistory +
               '}';
    }
}
