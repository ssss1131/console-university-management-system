package universityManagementSystem.models.student;

import universityManagementSystem.enums.academic.MasterProgram;

public class Master extends GraduateStudent {
    private MasterProgram masterProgram;

    private MasterProgram getMasterProgram() {
        return this.masterProgram;
    }

    private void setMasterProgram(MasterProgram masterProgram) {
        this.masterProgram = masterProgram;
    }
}
