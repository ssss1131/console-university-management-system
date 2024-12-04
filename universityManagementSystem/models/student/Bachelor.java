package universityManagementSystem.models.student;

import universityManagementSystem.enums.academic.Specialization;

public class Bachelor {
    private Specialization  specialization;

    private Specialization  getSpecialization() {
        return this.specialization;
    }

    private void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
