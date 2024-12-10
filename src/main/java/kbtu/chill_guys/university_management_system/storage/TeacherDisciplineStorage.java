package main.java.kbtu.chill_guys.university_management_system.storage;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;

import java.io.Serializable;
import java.util.*;

public class TeacherDisciplineStorage implements Serializable {

    private Map<Teacher, Map<Semester, List<Discipline>>> teacherDisciplineHistory = new HashMap<>();

    public void addDiscipline(Teacher teacher, Semester semester, Discipline discipline) {
        teacherDisciplineHistory
                .computeIfAbsent(teacher, k -> new HashMap<>())
                .computeIfAbsent(semester, k -> new ArrayList<>())
                .add(discipline);
    }

    public List<Discipline> getDisciplines(Teacher teacher, Semester semester) {
        return teacherDisciplineHistory
                .getOrDefault(teacher, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyList());
    }


    public Map<Teacher, Map<Semester, List<Discipline>>> getTeacherDisciplineHistory() {
        return teacherDisciplineHistory;
    }


    @Override
    public String toString() {
        return "TeacherDisciplineStorage{" +
               "teacherDisciplineHistory=" + teacherDisciplineHistory +
               '}';
    }
}
