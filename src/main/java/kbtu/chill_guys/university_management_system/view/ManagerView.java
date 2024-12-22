package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.employee.ResearchSupervisor;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;

import java.util.List;
import java.util.Map;

public interface ManagerView {
    Map<String, Object> getPostInput();

    void displayPostAdded(Post post);

    void displayDisciplinesByStatus(List<Discipline> assignedDisciplines, List<Discipline> cancelledDisciplines);

    Discipline getNewDisciplineInput();

    void showRequestSentConfirmation();

    List<Discipline> selectDisciplinesToFinalize(List<Discipline> approvedDisciplines);

    Semester getSemesterInput();

    StudentRole getStudentRoleInput();

    int getCourseInput();

    List<Discipline> selectDisciplinesForCourse(List<Discipline> availableDisciplines);

    Program selectProgram(StudentRole role);

    void showRegistrationInfo(Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> registrationMap, Semester semester);

    void showSemesterInfo(Semester semester);

    void showSuccessClosingRegistration(Semester semester);

    void showNoDisciplinesAvailableMessage();

    void showNoTeachersAvailableMessage();

    void showDisciplineAssignedMessage(Discipline discipline, Teacher teacher);

    Discipline selectDiscipline(List<Discipline> disciplines);

    Teacher selectTeacher(List<Teacher> teachers);

    GraduateStudent showFreeStudents(List<GraduateStudent> students);

    ResearchSupervisor showSupervisors(List<ResearchSupervisor> researchSupervisors);
}
