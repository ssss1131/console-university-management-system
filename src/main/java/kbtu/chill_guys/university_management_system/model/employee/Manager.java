package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewRequests;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewTeachers;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;

import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public class Manager extends Employee implements CanViewRequests, CanViewTeachers {
    private ManagerType managerType;

    public Manager() {}

    public Manager(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   Vector<Post> notifications, int salary, ManagerType managerType) {
        super(id, role, email, password, salt, firstName, lastName, notifications, salary);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() {
        return this.managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return managerType == manager.managerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerType);
    }

    @Override
    public String toString() {
        return "Manager{" +
               "managerType=" + managerType +
               "} " + super.toString();
    }
}
