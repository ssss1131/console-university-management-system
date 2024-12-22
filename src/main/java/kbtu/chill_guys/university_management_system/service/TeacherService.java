package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;
import main.java.kbtu.chill_guys.university_management_system.storage.TeacherDisciplineStorage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeacherService {
    private final TeacherDisciplineStorage storage = TeacherDisciplineStorage.getInstance();
    private final StudentDisciplineStorage studentStorage = StudentDisciplineStorage.getInstance();
    private final UserRepository userRepository = new UserRepository();



    public List<Discipline> getDisciplines(Teacher teacher, Semester semester) {
        return storage.getDisciplines(teacher, semester);
    }

    public List<Student> getStudents(Teacher teacher, Semester semester, Discipline discipline) {
        return storage.getStudentsInDiscipline(teacher, semester, discipline);
    }

    public void addLessonRecord(Teacher teacher, Semester semester, Discipline discipline, Student student, LocalDate date, String lessonName, Attendance attendance, Double grade, String comment) {
        if (storage.isAttestationClosed(discipline)) {
            throw new IllegalStateException("Cannot add grades. Attestation for this discipline is already closed.");
        }

        LessonRecord record = new LessonRecord(date, lessonName, attendance, grade, comment);
        storage.addLessonRecord(teacher, semester, discipline, student, record);
    }

    public void assignDisciplineToTeacher(Teacher teacher, Discipline discipline) {
        storage.assignDisciplineToTeacher(teacher, discipline.getSemester(), discipline);

        List<Student> registeredStudents = studentStorage.getStudentsByDiscipline(discipline);

        for (Student student : registeredStudents) {
            storage.registerStudentToTeacherDiscipline(teacher, discipline.getSemester(), discipline, student);
        }
    }

    public List<Teacher> getAllTeachers() {
        return userRepository.findUsersByRole(UserRole.TEACHER).stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .collect(Collectors.toList());
    }

    public List<LessonRecord> getLessonRecords(Teacher teacher, Semester semester, Discipline discipline, Student student) {
        return storage.getLessonRecordsForStudent(teacher, semester, discipline, student);
    }

    public void closeAttestation(Discipline selectedDiscipline ) {
        storage.closeAttestation(selectedDiscipline);
    }
}
