package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Discipline implements Serializable {
    private String code;
    private String name;
    private School school;
    private int credits;
    private Semester semester;
    private StudentRole targetAudience;
    private Set<Program> targetSpecializations;
    private Set<String> prerequisites; // set from codes of disciplines
    private CourseType courseType;

    public Discipline(String code, String name, School school, int credits, Semester semester, StudentRole targetAudience,Set<Program> programs, Set<String> prerequisites, CourseType courseType) {
        this.code = code;
        this.name = name;
        this.school = school;
        this.credits = credits;
        this.targetSpecializations = programs;
        this.semester = semester;
        this.targetAudience = targetAudience;
        this.prerequisites = prerequisites;
        this.courseType = courseType;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public School getSchool() {
        return school;
    }

    public int getCredits() {
        return credits;
    }

    public Semester getSemester() {
        return semester;
    }

    public StudentRole getTargetAudience() {
        return targetAudience;
    }

    public Set<String> getPrerequisites() {
        return prerequisites;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public Set<Program> getTargetSpecializations() {
        return targetSpecializations;
    }

    public static class Builder {
        private String code;
        private String name;
        private School school;
        private int credits;
        private Semester semester;
        private StudentRole targetAudience;
        private Set<Program> targetSpecializations;
        private Set<String> prerequisites = new HashSet<>();
        private CourseType courseType;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder school(School school) {
            this.school = school;
            return this;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder targetAudience(StudentRole targetAudience) {
            this.targetAudience = targetAudience;
            return this;
        }

        public Builder targetSpecializations(Set<Program> program){
            this.targetSpecializations = program != null ? program : new HashSet<>();
            return this;
        }

        public Builder prerequisites(Set<String> prerequisites) {
            this.prerequisites = prerequisites != null ? prerequisites : new HashSet<>();
            return this;
        }

        public Builder courseType(CourseType courseType) {
            this.courseType = courseType;
            return this;
        }

        public Discipline build() {
            if (code == null || name == null || school == null || semester == null || targetAudience == null || courseType == null || targetSpecializations == null) {
                throw new IllegalArgumentException("All required fields must be provided!");
            }
            return new Discipline(code, name, school, credits, semester, targetAudience,targetSpecializations, prerequisites, courseType);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }


    @Override
    public String toString() {
        return "Discipline{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", school=" + school +
               ", credits=" + credits +
               ", semester=" + semester +
               ", targetAudience=" + targetAudience +
               ", prerequisites=" + prerequisites +
               ", specializations=" + targetSpecializations +
               ", courseType=" + courseType +
               '}';
    }
}
