package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
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

    Semester getSemester(Set<Semester> semesters);

    void showExactDisciplines(List<Discipline> disciplines);

    void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester);

    void showMarksHeader();

    void showMarkRow(LessonRecord record);

    void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence);

    void showNoMarksMessage(Discipline discipline);

    void showNoSemesterSelectedMessage();

    void showDiscipline(Discipline discipline);

    void showNoDisciplinesAvailableMessage();

    void showSemesterHeader(Semester semester);

    void showDiploma(DiplomaProject project, GraduateStudent student);

    String getOrganizationName();

    void showAlreadyExistingOrganizationName();

    String getOrganizationDescription();

    Organization selectOrganization(List<Organization> organizations);

    OrganizationRole selectOrganizationRole(List<OrganizationRole> availableRoles);

    void showOrganizationInfo(List<Organization> organization);

    void showClosedRegistration();

    void noneAvailableDisciplines();
}
