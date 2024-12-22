package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ORGANIZATION_PATH;

public class OrganizationRepository extends AbstractRepository<Organization>  {

    public OrganizationRepository() {
        super(ORGANIZATION_PATH);
    }



}
