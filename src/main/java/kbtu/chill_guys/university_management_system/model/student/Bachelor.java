package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;

import java.time.LocalDate;
import java.util.UUID;

public class Bachelor extends Student {
    private Specialization specialization;

    public Bachelor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    School school, LocalDate enrollmentDate, double gpa, int credits,
                    int studyDuration, Organization organization, Specialization specialization) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits,
                studyDuration, organization);
        this.specialization = specialization;
    }

    public Bachelor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    School school, LocalDate enrollmentDate, Integer credits,
                    Integer studyDuration, Specialization specialization) {
        super(id, role, email, password, salt, firstName, lastName, school, enrollmentDate, credits, studyDuration);
        this.specialization = specialization;
    }

    @Override
    public Program getProgram() {
        return specialization;
    }

    @Override
    public StudentRole getStudentRole() {
        return StudentRole.BACHELOR;
    }

    public Specialization getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }


    @Override
    public String toString() {
        return "Bachelor{" +
               "specialization=" + specialization +
               "} " + super.toString();
    }
}
