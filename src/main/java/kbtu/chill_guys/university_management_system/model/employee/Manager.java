package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;

public class Manager implements CanViewRequests, CanViewTeachers {
    private ManagerType ManagerType;

    public ManagerType getManagerType() {
        return this.ManagerType;
    }

    public void setManagerType(ManagerType ManagerType) {
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
