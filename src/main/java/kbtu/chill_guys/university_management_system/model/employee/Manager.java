package universityManagementSystem.models.employee;

import universityManagementSystem.permissions.CanViewRequests;
import universityManagementSystem.permissions.CanViewTeachers;
import universityManagementSystem.enums.organization.ManagerType;

public class Manager implements CanViewRequests, CanViewTeachers {
    private ManagerType ManagerType;

    private ManagerType getManagerType() {
        return this.ManagerType;
    }

    private void setManagerType(ManagerType ManagerType) {
        this.ManagerType = ManagerType;
    }

    public String makeReport() {
        //TODO
        return "";
    }

    public void addNews() {
        //TODO
    }

    public void assignCourseToTeacher() {
        //TODO
    }

    public void addCourseForRegistration() {
        //TODO
    }

    public boolean approveStudentRegistration() {
        //TODO
        return false;
    }
}
