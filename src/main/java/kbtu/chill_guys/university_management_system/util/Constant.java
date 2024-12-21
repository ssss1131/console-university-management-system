package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Constant {


    private Constant(){
    }

    public static final Path BASE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "db");
    public static final Path USERS_PATH = Paths.get("account.ser");
    public static final Path DISCIPLINE_PATH = Paths.get("academic_db","discipline.ser");
    public static final Path JOURNAL_PATH = Paths.get("media_db","journal.ser");
    public static final Path POST_PATH = Paths.get("media_db", "post.ser");
    public static final Path LOG_FILE_PATH = Path.of("logs", "application.log.0");

    public static final Path DISCIPLINE_STATUS_PATH = Paths.get("academic_db","discipline_status.ser");
    public static final Path STUDENT_DISCIPLINE_PATH = Paths.get("academic_db", "student_discipline.ser");
    public static final Path DISCIPLINE_REGISTRATION_PATH = Paths.get("academic_db", "discipline_registration.ser");
    public static final Path JOURNAL_STORAGE_PATH = Paths.get("media_db", "journal_storage.ser");

    public static final Path RESEARCHERS_PATH = Paths.get("research_db", "researchers.ser");
    public static final Path RESEARCH_PAPER_PATH = Paths.get("research_db", "research_paper.ser");
    public static final Path RESEARCH_PROJECT_PATH = Paths.get("research_db", "research_project.ser");


    public static final String USER_ROLE_ATTRIBUTE = "role";
    public static final String EMAIL_ATTRIBUTE = "email";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String SALT_ATTRIBUTE = "salt";
    public static final String FIRSTNAME_ATTRIBUTE = "firstName";
    public static final String LASTNAME_ATTRIBUTE = "lastName";
    public static final String SCHOOL_ATTRIBUTE = "school";
    public static final String ENROLLMENT_DATE_ATTRIBUTE = "enrollmentDate";
    public static final String CREDITS_ATTRIBUTE = "credits";
    public static final String STUDY_DURATION_ATTRIBUTE = "studyDuration";
    public static final String PROGRAM_ATTRIBUTE = "program";
    public static final String ORGANIZATION_ATTRIBUTE = "organization";
    public static final String RATING_ATTRIBUTE = "rating";
    public static final String TEACHING_DEGREE_ATTRIBUTE = "teachingDegree";
    public static final String SALARY_ATTRIBUTE = "salary";
    public static final String MANAGER_TYPE_ATTRIBUTE = "managerType";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String CONTENT_ATTRIBUTE = "content";
    public static final String AUTHOR_ATTRIBUTE = "author";
    public static final String DATE_ATTRIBUTE = "date";

    public static final List<String> researcherMethods = List.of("get my research papers", "add new research paper");
    public static final UserRole[] ALL_RESEARCH_ROLES = {
            UserRole.BACHELOR,
            UserRole.MASTER,
            UserRole.PHD,
            UserRole.PROFESSOR,
            UserRole.RESEARCH_SUPERVISOR,
            UserRole.MANAGER,
            UserRole.TEACHER
    };

    public static final String CANCEL_INPUT = "cancel";
    public static final Integer MIN_H_INDEX = 3;
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

}
