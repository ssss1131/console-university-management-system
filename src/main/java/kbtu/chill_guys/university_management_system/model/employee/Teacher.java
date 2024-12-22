package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.permission.CanViewStudents;

import java.util.UUID;

import static main.java.kbtu.chill_guys.university_management_system.util.NumericToEnumMapper.mapRating;

/**
 * The {@code Teacher} class represents a teacher in the university management system.
 * It extends the {@link Employee} class and implements the {@link CanViewStudents} interface,
 * enabling teachers to view student information and manage teaching-related attributes.
 */
public class Teacher extends Employee implements CanViewStudents {

    /**
     * The rating of the teacher, derived from their score.
     */
    private Rating rating;

    /**
     * The score of the teacher, used to calculate their rating.
     */
    private int score;

    /**
     * The school to which the teacher belongs.
     */
    private School school;

    /**
     * The teaching degree of the teacher.
     */
    private TeachingDegree teachingDegree;

    /**
     * Constructs a new {@code Teacher} with specified attributes, including teaching degree.
     *
     * @param id             the unique identifier of the teacher
     * @param role           the role of the teacher in the system
     * @param email          the email address of the teacher
     * @param password       the password of the teacher
     * @param salt           the salt used for password encryption
     * @param firstName      the first name of the teacher
     * @param lastName       the last name of the teacher
     * @param salary         the salary of the teacher
     * @param initialScore   the initial score of the teacher
     * @param school         the school to which the teacher belongs
     * @param teachingDegree the teaching degree of the teacher
     */
    public Teacher(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   int salary, int initialScore, School school, TeachingDegree teachingDegree) {
        super(id, role, email, password, salt, firstName, lastName, salary);
        this.score = initialScore;
        this.rating = mapRating(initialScore);
        this.school = school;
        this.teachingDegree = teachingDegree;
    }

    /**
     * Constructs a new {@code Teacher} with specified attributes without teaching degree.
     *
     * @param id           the unique identifier of the teacher
     * @param role         the role of the teacher in the system
     * @param email        the email address of the teacher
     * @param password     the password of the teacher
     * @param salt         the salt used for password encryption
     * @param firstName    the first name of the teacher
     * @param lastName     the last name of the teacher
     * @param salary       the salary of the teacher
     * @param initialScore the initial score of the teacher
     * @param school       the school to which the teacher belongs
     */
    public Teacher(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName,
                   int salary, int initialScore, School school) {
        super(id, role, email, password, salt, firstName, lastName, salary);
        this.score = initialScore;
        this.rating = mapRating(initialScore);
        this.school = school;
    }

    /**
     * Default constructor for the {@code Teacher} class.
     */
    public Teacher() {
    }

    /**
     * Gets the rating of the teacher.
     *
     * @return the rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Updates the score and recalculates the teacher's rating.
     *
     * @param newScore the new score for the teacher
     */
    public void setRating(int newScore) {
        this.score = newScore;
        this.rating = mapRating(newScore);
    }

    /**
     * Gets the score of the teacher.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the school of the teacher.
     *
     * @return the school
     */
    public School getSchool() {
        return school;
    }

    /**
     * Sets the school of the teacher.
     *
     * @param school the new school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * Gets the teaching degree of the teacher.
     *
     * @return the teaching degree
     */
    public TeachingDegree getTeachingDegree() {
        return teachingDegree;
    }

    /**
     * Sets the teaching degree of the teacher.
     *
     * @param teachingDegree the new teaching degree
     */
    public void setTeachingDegree(TeachingDegree teachingDegree) {
        this.teachingDegree = teachingDegree;
    }

    /**
     * Provides a string representation of the {@code Teacher} object.
     *
     * @return a string containing the teacher's details
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "rating=" + rating +
                ", score=" + score +
                ", school=" + school +
                ", teachingDegree=" + teachingDegree +
                "} " + super.toString();
    }
}
