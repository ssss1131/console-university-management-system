package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.OrganizationRepository;

import java.util.*;

public class OrganizationService {

    private final OrganizationRepository organizationRepository = new OrganizationRepository();

    public void addNewMemberToOrganization(Student student, Organization organization, OrganizationRole role) {
        Optional<Organization> first = organizationRepository.getAllLines()
                .stream().filter(organization1 -> organization1.equals(organization)).findFirst();
        if (first.isPresent()) {
            Organization org = first.get();
            org.addMember(student, role);
            organizationRepository.update(org);
        }
    }

    public List<OrganizationRole> getAvailableRoles(Organization organization) {
        List<OrganizationRole> availableRoles = new ArrayList<>();
        for (OrganizationRole role : OrganizationRole.values()) {
            if (!organization.getMembers().containsValue(role)) {
                availableRoles.add(role);
            }
        }
        if (!availableRoles.contains(OrganizationRole.MEMBER)) {
            availableRoles.add(OrganizationRole.MEMBER);
        }
        return availableRoles;
    }


    public boolean isUniqueName(String name) {
        return organizationRepository.getAllLines().stream()
                .noneMatch(organization -> organization.getName().equalsIgnoreCase(name));
    }

    public void save(Organization organization, Student student) {
        organization.addMember(student, OrganizationRole.PRESIDENT);
        organizationRepository.addLine(organization);
    }

    public List<Organization> findOrganization(Student student) {
        return organizationRepository.getAllLines().stream()
                .filter(organization -> organization.getMembers().containsKey(student))
                .toList();
    }

    public List<Organization> getAllNotJoinedOrganizations(Student user) {
        return organizationRepository.getAllLines().stream()
                .filter(organization -> !organization.getMembers().containsKey(user))
                .toList();
    }
}
