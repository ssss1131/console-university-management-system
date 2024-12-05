package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.GPA;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Employee;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class UserFactory {
    public static User createUser(String type, Map<String, Object> data) {
        Vector<Post> notifications = (Vector<Post>) data.get("notifications");

        if (notifications == null) {
            notifications = new Vector<>();
        }

        switch (type.toLowerCase()) {
            case "admin":
                return new Admin(
                        UUID.fromString((String) data.get("id")),
                        (String) data.get("email"),
                        (String) data.get("firstName"),
                        (String) data.get("lastName"),
                        notifications
                ) {
                };
            case "student":
                return new Student(
                        UUID.fromString((String) data.get("id")),
                        (String) data.get("email"),
                        (String) data.get("firstName"),
                        (String) data.get("lastName"),
                        notifications,
                        (School) data.get("school"),
                        LocalDate.parse((String) data.get("enrollmentDate")),
                        (GPA) data.get("school"),
                        Integer.parseInt((String) data.get("credits")),
                        Integer.parseInt((String) data.get("studyDuration")),
                        (Organization) data.get("organization")
                );
            case "employee":
                return new Employee(
                        UUID.fromString((String) data.get("id")),
                        (String) data.get("email"),
                        (String) data.get("firstName"),
                        (String) data.get("lastName"),
                        notifications,
                        Integer.parseInt((String) data.get("salary")),
                        (Teacher) data.get("teacher")
                );
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}