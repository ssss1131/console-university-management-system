package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;

import java.util.Objects;

public class Master extends GraduateStudent {
    private MasterProgram masterProgram;

    public MasterProgram getMasterProgram() {
        return this.masterProgram;
    }

    public void setMasterProgram(MasterProgram masterProgram) {
        this.masterProgram = masterProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Master master = (Master) o;
        return masterProgram == master.masterProgram;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), masterProgram);
    }

    @Override
    public String toString() {
        return "Master{" +
               "masterProgram=" + masterProgram +
               "} " + super.toString();
    }
}
