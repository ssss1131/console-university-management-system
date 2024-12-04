package universityManagementSystem.student;

import universityManagementSystem.enums.Specialization;

public class Bachelor {
    private Specialization  specialization;

    private Specialization  getSpecialization() {
        return this.specialization;
    }

    private void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
