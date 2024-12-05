package main.java.kbtu.chill_guys.university_management_system.model.academic;

public class Subject {
    private invalid attribute;
    private School school;
    private String code;
    private String name;
    private Integer credits;
    private GradeBook gradebook;
    private Vector<Subject> prerequisites;
    private Vector<School, CourseType> subjectType;
    private School school;
    private CourseType courseType;

    public invalid getAttribute() {
        return this.attribute;
    }

    public invalid setAttribute(invalid attribute) {
        this.attribute = attribute;
    }

    private School getSchool() {
        return this.school;
    }

    private School setSchool(School school) {
        this.school = school;
    }

    private String getCode() {
        return this.code;
    }

    private String setCode(String code) {
        this.code = code;
    }

    private String getName() {
        return this.name;
    }

    private String setName(String name) {
        this.name = name;
    }

    private Integer getCredits() {
        return this.credits;
    }

    private Integer setCredits(Integer credits) {
        this.credits = credits;
    }

    private GradeBook getGradebook() {
        return this.gradebook;
    }

    private GradeBook setGradebook(GradeBook gradebook) {
        this.gradebook = gradebook;
    }

    private Vector<Subject> getPrerequisites() {
        return this.prerequisites;
    }

    private Vector<Subject> setPrerequisites(Vector<Subject> prerequisites) {
        this.prerequisites = prerequisites;
    }

    private Vector<School, CourseType> getSubjectType() {
        return this.subjectType;
    }

    private Vector<School, CourseType> setSubjectType(Vector<School, CourseType> subjectType) {
        this.subjectType = subjectType;
    }

    private School getSchool() {
        return this.school;
    }

    private School setSchool(School school) {
        this.school = school;
    }

    private CourseType getCourseType() {
        return this.courseType;
    }

    private CourseType setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
    

    //                          Operations                                  

    public double getMinandMax() {
        //TODO
        return 0.0;
    }

    public Vector<Subject> getPrerequisites() {
        //TODO
        return null;
    }

    public void viewGradebook() {
        //TODO
        return null;
    }

    public void getTranscript() {
        //TODO
        return null;
    }
    
    
}
