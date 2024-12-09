package main.java.kbtu.chill_guys.university_management_system.model;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.*;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Dean;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Manager;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Bachelor;
import main.java.kbtu.chill_guys.university_management_system.model.student.Master;
import main.java.kbtu.chill_guys.university_management_system.model.student.PHD;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Logger;

public final class UserFactory {

    private static final Logger logger = Logger.getLogger(UserFactory.class.getName());

    private UserFactory() {

    }

    public static User createUser(UserRole role, Map<String, Object> data) {
        UUID uuid = UUID.randomUUID();
        Vector<Post> notifications = new Vector<>();

        String email = (String) data.get("email");
        String password = (String) data.get("password");
        String salt = (String) data.get("salt");
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");

        switch (role) {
            case ADMIN:
                return new Admin(uuid, role, email, password, salt, firstName, lastName, notifications);
            case TEACHER:
                return new Teacher(uuid, role, email, password, salt, firstName, lastName, notifications,
                        (Integer) data.get("salary"),
                        (Rating) data.get("rating"),
                        (School) data.get("school"),
                        (TeachingDegree) data.get("teachingDegree")
                );
            case MANAGER:
                return new Manager(uuid, role, email, password, salt, firstName, lastName, notifications,
                        (Integer) data.get("salary"),
                        (ManagerType) data.get("managerType")
                );
            case DEAN:
                return new Dean(uuid, role, email, password, salt, firstName, lastName, notifications,
                        (Integer) data.get("salary")
                );
            case BACHELOR, PHD, MASTER:
                School school = (School) data.get("school");
                LocalDate enrollmentDate = LocalDate.parse((String) data.get("enrollmentDate"));
                double gpa = (double) data.get("gpa");
                int credits = (int) data.get("credits");
                int studyDuration = (int) data.get("studyDuration");

                switch (role) {
                    case BACHELOR:
                        Specialization specialization = (Specialization) data.get("program");
                        return new Bachelor(uuid, role, email, password, salt, firstName, lastName, notifications, school,
                                enrollmentDate, gpa, credits, studyDuration, specialization);

                    case MASTER:
                        MasterProgram masterProgram = (MasterProgram) data.get("program");
                        return new Master(uuid, role, email, password, salt, firstName, lastName, notifications, school,
                                enrollmentDate, gpa, credits, studyDuration, masterProgram);

                    case PHD:
                        PhdProgram phdProgram = (PhdProgram) data.get("program");
                        return new PHD(uuid, role, email, password, salt, firstName, lastName, notifications, school,
                                enrollmentDate, gpa, credits, studyDuration, phdProgram);

                    default:
                        throw new IllegalArgumentException("Неверная роль: " + role);
                }


            default:
                logger.warning("Not handled role, need some validation");
                throw new IllegalArgumentException("Unknown user type: " + role);
        }
    }
}
