package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;

import java.util.Objects;

public class Bachelor extends Student {
    private Specialization  specialization;

    public Specialization  getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bachelor bachelor = (Bachelor) o;
        return specialization == bachelor.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialization);
    }

    @Override
    public String toString() {
        return "Bachelor{" +
               "specialization=" + specialization +
               "} " + super.toString();
    }
}
