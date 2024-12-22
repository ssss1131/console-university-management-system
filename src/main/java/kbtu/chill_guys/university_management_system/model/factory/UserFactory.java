package main.java.kbtu.chill_guys.university_management_system.model.factory;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.*;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.Admin;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.employee.*;
import main.java.kbtu.chill_guys.university_management_system.model.student.Bachelor;
import main.java.kbtu.chill_guys.university_management_system.model.student.Master;
import main.java.kbtu.chill_guys.university_management_system.model.student.PHD;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;

public final class UserFactory {

    private static final Logger logger = Logger.getLogger(UserFactory.class.getName());

    private UserFactory() {

    }

    public static User createUser(UserRole role, Map<String, Object> data) {
        UUID uuid = UUID.randomUUID();

        String email = (String) data.get(EMAIL_ATTRIBUTE);
        String password = (String) data.get(PASSWORD_ATTRIBUTE);
        String salt = (String) data.get(SALT_ATTRIBUTE);
        String firstName = (String) data.get(FIRSTNAME_ATTRIBUTE);
        String lastName = (String) data.get(LASTNAME_ATTRIBUTE);

        switch (role) {
            case ADMIN:
                return new Admin(uuid, role, email, password, salt, firstName, lastName);
            case TEACHER:
                return new Teacher(uuid, role, email, password, salt, firstName, lastName,
                        (Integer) data.get(SALARY_ATTRIBUTE),
                        (Integer) data.get(RATING_ATTRIBUTE),
                        (School) data.get(SCHOOL_ATTRIBUTE),
                        (TeachingDegree) data.get(TEACHING_DEGREE_ATTRIBUTE)
                );
            case PROFESSOR:
                return new Professor(uuid, role, email, password, salt, firstName, lastName,
                        (Integer) data.get(SALARY_ATTRIBUTE),
                        (Integer) data.get(RATING_ATTRIBUTE),
                        (School) data.get(SCHOOL_ATTRIBUTE));
            case RESEARCH_SUPERVISOR:
                return new ResearchSupervisor(uuid, role, email, password, salt, firstName, lastName,
                        (Integer) data.get(SALARY_ATTRIBUTE));
            case MANAGER:
                return new Manager(uuid, role, email, password, salt, firstName, lastName,
                        (Integer) data.get(SALARY_ATTRIBUTE),
                        (ManagerType) data.get(MANAGER_TYPE_ATTRIBUTE)
                );
            case DEAN:
                return new Dean(uuid, role, email, password, salt, firstName, lastName,
                        (Integer) data.get(SALARY_ATTRIBUTE)
                );
            case BACHELOR, PHD, MASTER:
                School school = (School) data.get(SCHOOL_ATTRIBUTE);
                LocalDate enrollmentDate = LocalDate.parse((String) data.get(ENROLLMENT_DATE_ATTRIBUTE));
                int credits = (int) data.get(CREDITS_ATTRIBUTE);
                int studyDuration = (int) data.get(STUDY_DURATION_ATTRIBUTE);

                switch (role) {
                    case BACHELOR:
                        Specialization specialization = (Specialization) data.get(PROGRAM_ATTRIBUTE);
                        return new Bachelor(uuid, role, email, password, salt, firstName, lastName, school,
                                enrollmentDate, credits, studyDuration, specialization);

                    case MASTER:
                        MasterProgram masterProgram = (MasterProgram) data.get(PROGRAM_ATTRIBUTE);
                        return new Master(uuid, role, email, password, salt, firstName, lastName, school,
                                enrollmentDate, credits, studyDuration, masterProgram);

                    case PHD:
                        PhdProgram phdProgram = (PhdProgram) data.get(PROGRAM_ATTRIBUTE);
                        return new PHD(uuid, role, email, password, salt, firstName, lastName, school,
                                enrollmentDate, credits, studyDuration, phdProgram);

                    default:
                        logger.warning("Not handled role, need some validation");
                        throw new IllegalArgumentException("incorrect role:  " + role);
                }


            default:
                logger.warning("Not handled role, need some validation");
                throw new IllegalArgumentException("Unknown user type: " + role);
        }
    }
}
