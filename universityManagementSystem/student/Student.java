package universityManagementSystem.student;


/**
* @generated
*/
public class Student extends BaseUserBaseUser implements CanViewCourses, CanBeResearcher, CanViewMarks, CanViewTeachers {
    
    /**
    * @generated
    */
    private String ?studentId;
    
    /**
    * @generated
    */
    private Integer ;
    
    /**
    * @generated
    */
    private School school;
    
    /**
    * @generated
    */
    private LocalDate enrollmentDate;
    
    /**
    * @generated
    */
    private Gpa gpa;
    
    /**
    * @generated
    */
    private Integer credits;
    
    /**
    * @generated
    */
    private Integer studyDuration;
    
    /**
    * @generated
    */
    private universityManagementSystem.Organization organization;
    
    
    
    /**
    * @generated
    */
    private String get?studentId() {
        return this.?studentId;
    }
    
    /**
    * @generated
    */
    private String set?studentId(String ?studentId) {
        this.?studentId = ?studentId;
    }
    
    /**
    * @generated
    */
    public Integer get() {
        return this.;
    }
    
    /**
    * @generated
    */
    public Integer set(Integer ) {
        this. = ;
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
    private LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }
    
    /**
    * @generated
    */
    private LocalDate setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    /**
    * @generated
    */
    private Gpa getGpa() {
        return this.gpa;
    }
    
    /**
    * @generated
    */
    private Gpa setGpa(Gpa gpa) {
        this.gpa = gpa;
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
    private Integer getStudyDuration() {
        return this.studyDuration;
    }
    
    /**
    * @generated
    */
    private Integer setStudyDuration(Integer studyDuration) {
        this.studyDuration = studyDuration;
    }
    
    /**
    * @generated
    */
    private universityManagementSystem.Organization getOrganization() {
        return this.organization;
    }
    
    /**
    * @generated
    */
    private universityManagementSystem.Organization setOrganization(Organization organization) {
        this.organization = organization;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public boolean registerForCourse() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public boolean dropCourse() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public void rateTeacher() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public Transcript viewTranscript() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public Vector<Mark> viewMarks() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public boolean joinOrganization() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public boolean leaveOrganization() {
        //TODO
        return false;
    }
    
    /**
    * @generated
    */
    public Map<Course, Integer> viewAttendance() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public String viewAcademicStanding() {
        //TODO
        return "";
    }
    
    
}
