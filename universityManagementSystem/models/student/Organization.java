package universityManagementSystem.models.student;

import universityManagementSystem.enums.academic.OrganizationRole;

import java.util.Map;

public class Organization {
    private String name;
    private String description;
    private Map<Student, OrganizationRole> members;

    private String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private Map<Student, OrganizationRole> getMembers() {
        return this.members;
    }

    private void setMembers(Map<Student, OrganizationRole> members) {
        this.members = members;
    }

    public void addMember() {
        //TODO
    }

    public boolean changeRole() {
        //TODO
        return false;
    }

    public void removeMember() {
        //TODO
    }
}
