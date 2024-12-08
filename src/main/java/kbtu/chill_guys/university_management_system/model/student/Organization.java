package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Organization implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, members);
    }

    @Override
    public String toString() {
        return "Organization{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", members=" + members +
               '}';
    }
}
