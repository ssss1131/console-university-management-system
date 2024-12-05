package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;

public class Bachelor {
    private Specialization  specialization;

    public Specialization  getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
