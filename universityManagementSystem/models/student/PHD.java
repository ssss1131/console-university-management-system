package universityManagementSystem.models.student;

import universityManagementSystem.enums.academic.PhdProgram;

public class PHD extends GraduateStudent {
    private PhdProgram phdProgram;

    private PhdProgram getPhdProgram() {
        return this.phdProgram;
    }

    private void setPhdProgram(PhdProgram phdProgram) {
        this.phdProgram = phdProgram;
    }
}
