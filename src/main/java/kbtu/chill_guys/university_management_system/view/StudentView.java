package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentView {
    void displayAvailableDisciplines(List<Discipline> disciplines);

    List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines);

    void displayRegistrationConfirmation(List<Discipline> registeredDisciplines);

    void displayInfo(Student student);

    void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester);

    void showMessage(String message);

    Semester getSemester(Set<Semester> semesters);

    void showExactDisciplines(List<Discipline> disciplines);

    void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester);

    void showDiploma(DiplomaProject project, GraduateStudent student);
}
