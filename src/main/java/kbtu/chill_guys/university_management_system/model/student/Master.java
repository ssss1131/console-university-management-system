package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;

public class Master extends GraduateStudent {
    private MasterProgram masterProgram;

    public MasterProgram getMasterProgram() {
        return this.masterProgram;
    }

    public void setMasterProgram(MasterProgram masterProgram) {
        this.masterProgram = masterProgram;
    }
}
