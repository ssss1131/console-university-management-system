package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.GPA;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Vector;

public class Bachelor extends Student {
    private Specialization  specialization;

    public Bachelor() {}

    public Bachelor(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                    Vector<Post> notifications, School school, LocalDate enrollmentDate, GPA school1, int credits,
                    int studyDuration, Organization organization, Specialization specialization) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, school1, credits,
                studyDuration, organization);
        this.specialization = specialization;
    }

    public Specialization  getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
