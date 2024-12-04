package universityManagementSystem.model.Academic;


import universityManagementSystem.Vector;

/**
* @generated
*/
public class Subject {
    
    /**
    * @generated
    */
    private invalid attribute;
    
    /**
    * @generated
    */
    private School school;
    
    /**
    * @generated
    */
    private String code;
    
    /**
    * @generated
    */
    private String name;
    
    /**
    * @generated
    */
    private Integer credits;
    
    /**
    * @generated
    */
    private GradeBook gradebook;
    
    /**
    * @generated
    */
    private Vector<Subject> prerequisites;
    
    /**
    * @generated
    */
    private Vector<School, CourseType> subjectType;
    
    /**
    * @generated
    */
    private School school;
    
    /**
    * @generated
    */
    private CourseType courseType;
    
    
    
    /**
    * @generated
    */
    public invalid getAttribute() {
        return this.attribute;
    }
    
    /**
    * @generated
    */
    public invalid setAttribute(invalid attribute) {
        this.attribute = attribute;
    }
    
    /**
    * @generated
    */
    private School getSchool() {
        return this.school;
    }
    
    /**
    * @generated
    */
    private School setSchool(School school) {
        this.school = school;
    }
    
    /**
    * @generated
    */
    private String getCode() {
        return this.code;
    }
    
    /**
    * @generated
    */
    private String setCode(String code) {
        this.code = code;
    }
    
    /**
    * @generated
    */
    private String getName() {
        return this.name;
    }
    
    /**
    * @generated
    */
    private String setName(String name) {
        this.name = name;
    }
    
    /**
    * @generated
    */
    private Integer getCredits() {
        return this.credits;
    }
    
    /**
    * @generated
    */
    private Integer setCredits(Integer credits) {
        this.credits = credits;
    }
    
    /**
    * @generated
    */
    private GradeBook getGradebook() {
        return this.gradebook;
    }
    
    /**
    * @generated
    */
    private GradeBook setGradebook(GradeBook gradebook) {
        this.gradebook = gradebook;
    }
    
    /**
    * @generated
    */
    private Vector<Subject> getPrerequisites() {
        return this.prerequisites;
    }
    
    /**
    * @generated
    */
    private Vector<Subject> setPrerequisites(Vector<Subject> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    /**
    * @generated
    */
    private Vector<School, CourseType> getSubjectType() {
        return this.subjectType;
    }
    
    /**
    * @generated
    */
    private Vector<School, CourseType> setSubjectType(Vector<School, CourseType> subjectType) {
        this.subjectType = subjectType;
    }
    
    /**
    * @generated
    */
    private School getSchool() {
        return this.school;
    }
    
    /**
    * @generated
    */
    private School setSchool(School school) {
        this.school = school;
    }
    
    /**
    * @generated
    */
    private CourseType getCourseType() {
        return this.courseType;
    }
    
    /**
    * @generated
    */
    private CourseType setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public double getMinandMax() {
        //TODO
        return 0.0;
    }
    
    /**
    * @generated
    */
    public Vector<Subject> getPrerequisites() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public void viewGradebook() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public void getTranscript() {
        //TODO
        return null;
    }
    
    
}
