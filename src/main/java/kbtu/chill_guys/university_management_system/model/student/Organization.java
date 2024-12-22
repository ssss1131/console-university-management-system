package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Organization implements Serializable{
    private String name;
    private String description;
    private Map<Student, OrganizationRole> members = new HashMap<>();

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public void addMember(Student student, OrganizationRole role) {
        members.put(student, role);
    }

    public void changeRole(Student student, OrganizationRole role) {
        members.put(student, role);
    }

    public void removeMember(Student student) {
        members.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
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
