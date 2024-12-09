package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class StudentView {
    public void displayInfo(Student student) {
        System.out.println("Hello " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Your ID: " + student.getId());
        System.out.println("Study duration: " + student.getStudyDuration());
        System.out.println("School: " + student.getSchool());
        System.out.println("Organization: " + student.getOrganization());

    }

    public void displayTranscript() {


    }

    public void displayOrganizationsFeatures() {

    }

    public void displayAllTeachersForRating() {
        //TODO
    }

    public void displayAttendance() {
        //TODO
    }

    public void displayAcademicStanding() {
        //TODO
    }
    // Печать доступных годов
    public void printAvailableYears(Set<String> years) {
        System.out.println("Available years:");
        years.forEach(year -> System.out.println("  - " + year));
    }

    // Печать доступных периодов
    public void printAvailablePeriods(List<Period> periods) {
        System.out.println("Available periods:");
        periods.forEach(period -> System.out.println("  - " + period));
    }

    // Печать курсов
    public void printCourses(List<String> courses, String year, Period period) {
        System.out.println("Courses for " + year + " " + period + ":");
        if (courses.isEmpty()) {
            System.out.println("  No courses found.");
        } else {
            courses.forEach(course -> System.out.println("  - " + course));
        }
    }
    
    
}
