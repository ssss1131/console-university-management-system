package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;

import java.util.Map;

public class Organization {
    private String name;
    private String description;
    private Map<Student, OrganizationRole> members;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Student, OrganizationRole> getMembers() {
        return this.members;
    }

    public void setMembers(Map<Student, OrganizationRole> members) {
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
