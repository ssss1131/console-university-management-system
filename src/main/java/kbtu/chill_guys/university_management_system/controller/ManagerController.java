package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.service.CourseManagementService;
import main.java.kbtu.chill_guys.university_management_system.service.NewsManagementService;
import main.java.kbtu.chill_guys.university_management_system.service.ReportService;

public class ManagerController {
    private CourseManagementService courseManagementService;
    private ReportService reportService;
    private NewsManagementService newsManagementService;

    public CourseManagementService getCourseManagementService() {
        return this.courseManagementService;
    }

    public void setCourseManagementService(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    public ReportService getReportService() {
        return this.reportService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public NewsManagementService getNewsManagementService() {
        return this.newsManagementService;
    }

    public void setNewsManagementService(NewsManagementService newsManagementService) {
        this.newsManagementService = newsManagementService;
    }

    public void addCoursesForRegistration() {
        //TODO
    }

    public void assignCourseToTeacher() {
        //TODO
    }

    public boolean approveStudentRegistration() {
        //TODO
        return false;
    }

    public String makeReport() {
        //TODO
        return "";
    }

    public void addNews() {
        //TODO
    }
}
