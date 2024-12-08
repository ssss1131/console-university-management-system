package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;

import java.util.Objects;

public class PHD extends GraduateStudent {
    private PhdProgram phdProgram;

    public PhdProgram getPhdProgram() {
        return this.phdProgram;
    }

    public void setPhdProgram(PhdProgram phdProgram) {
        this.phdProgram = phdProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PHD phd = (PHD) o;
        return phdProgram == phd.phdProgram;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phdProgram);
    }

    @Override
    public String toString() {
        return "PHD{" +
               "phdProgram=" + phdProgram +
               "} " + super.toString();
    }
}
