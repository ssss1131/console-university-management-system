package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.GPA;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Dean;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Employee;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Manager;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class UserFactory {
    public static User createUser(UserRole role, Map<String, Object> data) {
        UUID uuid = UUID.randomUUID();
        Vector<Post> notifications = new Vector<>();

        switch (role) {
            case ADMIN:
                return new Admin(
                        uuid,
                        role,
                        (String) data.get("email"),
                        (String) data.get("password"),
                        (String) data.get("salt"),
                        (String) data.get("firstName"),
                        (String) data.get("lastName"),
                        notifications
                ) {
                };
            case TEACHER:
                return new Teacher(
                        uuid,
                        role,
                        (String) data.get("email"),
                        (String) data.get("password"),
                        (String) data.get("salt"),
                        (String) data.get("firstName"),
                        (String) data.get("lastName"),
                        notifications,
                        (Integer) data.get("salary"),
                        (Rating) data.get("rating"),
                        (School) data.get("school"),
                        (TeachingDegree) data.get("teachingDegree")
                );
            case MANAGER:
                return new Manager(
                    uuid,
                    role,
                    (String) data.get("email"),
                    (String) data.get("password"),
                    (String) data.get("salt"),
                    (String) data.get("firstName"),
                    (String) data.get("lastName"),
                    notifications,
                    (Integer) data.get("salary"),
                    (ManagerType) data.get("managerType")
                );
            case DEAN:
                return new Dean(
                    uuid,
                    role,
                    (String) data.get("email"),
                    (String) data.get("password"),
                    (String) data.get("salt"),
                    (String) data.get("firstName"),
                    (String) data.get("lastName"),
                    notifications,
                    (Integer) data.get("salary")
                );
            default:
                throw new IllegalArgumentException("Unknown user type: " + role);
        }
    }
}
