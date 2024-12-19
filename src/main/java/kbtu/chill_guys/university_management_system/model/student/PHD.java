package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Vector;

public class PHD extends GraduateStudent {
    private PhdProgram phdProgram;

    public PHD(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, PhdProgram phdProgram) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, credits, studyDuration);
        this.phdProgram = phdProgram;
    }

    public PHD(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, Organization organization, PhdProgram phdProgram) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, credits, studyDuration, organization);
        this.phdProgram = phdProgram;
    }

    public PhdProgram getPhdProgram() {
        return this.phdProgram;
    }

    public void setPhdProgram(PhdProgram phdProgram) {
        this.phdProgram = phdProgram;
    }

    @Override
    public Program getProgram() {
        return phdProgram;
    }

    @Override
    public StudentRole getStudentRole() {
        return StudentRole.PHD;
    }

    @Override
    public String toString() {
        return "PHD{" +
               "phdProgram=" + phdProgram +
               "} " + super.toString();
    }

    @Override
    public void addResearchPaper(ResearchProject researchProject) {

    }

    @Override
    public void addResearchProjects(ResearchProject researchProject) {

    }
}
