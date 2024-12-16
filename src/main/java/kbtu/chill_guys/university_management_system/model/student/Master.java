package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public class Master extends GraduateStudent {
    private MasterProgram masterProgram;

    public Master(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, Organization organization, MasterProgram masterProgram) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, credits, studyDuration, organization);
        this.masterProgram = masterProgram;
    }

    public Master(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, Integer credits, Integer studyDuration, MasterProgram masterProgram) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, credits, studyDuration);
        this.masterProgram = masterProgram;
    }

    public MasterProgram getMasterProgram() {
        return this.masterProgram;
    }

    public void setMasterProgram(MasterProgram masterProgram) {
        this.masterProgram = masterProgram;
    }

    @Override
    public Program getProgram() {
        return masterProgram;
    }

    @Override
    public StudentRole getStudentRole() {
        return StudentRole.MASTER;
    }


    @Override
    public String toString() {
        return "Master{" +
               "masterProgram=" + masterProgram +
               "} " + super.toString();
    }
}
