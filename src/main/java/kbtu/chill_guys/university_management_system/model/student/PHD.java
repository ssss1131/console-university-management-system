package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;

public class PHD extends GraduateStudent {
    private PhdProgram phdProgram;

    public PhdProgram getPhdProgram() {
        return this.phdProgram;
    }

    public void setPhdProgram(PhdProgram phdProgram) {
        this.phdProgram = phdProgram;
    }
}
