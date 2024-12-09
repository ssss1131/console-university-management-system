package main.java.kbtu.chill_guys.university_management_system.model.student;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public abstract class GraduateStudent extends Student implements Researcher {
    private DiplomaProject project;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public GraduateStudent(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, double gpa, Integer credits, Integer studyDuration, Organization organization) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, gpa, credits, studyDuration, organization);
    }

    public GraduateStudent(UUID id, UserRole role, String email, String password, String salt, String firstName, String lastName, Vector<Post> notifications, School school, LocalDate enrollmentDate, double gpa, Integer credits, Integer studyDuration) {
        super(id, role, email, password, salt, firstName, lastName, notifications, school, enrollmentDate, gpa, credits, studyDuration);
    }

    public DiplomaProject getProject() {
        return project;
    }

    public void setProject(DiplomaProject project) {
        this.project = project;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }
    
    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }
    
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }
    
    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GraduateStudent that = (GraduateStudent) o;
        return Objects.equals(project, that.project) && Objects.equals(researchProjects, that.researchProjects) && Objects.equals(researchPapers, that.researchPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), project, researchProjects, researchPapers);
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
               "project=" + project +
               ", researchProjects=" + researchProjects +
               ", researchPapers=" + researchPapers +
               "} " + super.toString();
    }
}
