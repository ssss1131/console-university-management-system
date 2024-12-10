package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.User;
//import main.java.kbtu.chill_guys.university_management_system.model.academic.Course;
//import main.java.kbtu.chill_guys.university_management_system.model.academic.Mark;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Request;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.service.*;

import java.util.Vector;

public class GeneralController {
    private DisciplineService disciplineService;
    private TeacherService teacherService;
    private RequestService requestService;
    private AuthService authService;
    private JournalService journalService;
    private NewsRetrievalService newsRetrievalService;

    public DisciplineService getCourseService() {
        return this.disciplineService;
    }

    public void setCourseService(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    public TeacherService getTeacherService() {
        return this.teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public RequestService getRequestService() {
        return this.requestService;
    }

    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    public AuthService getAuthService() {
        return this.authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public JournalService getJournalService() {
        return this.journalService;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    public NewsRetrievalService getNewsRetrievalService() {
        return this.newsRetrievalService;
    }

    public void setNewsRetrievalService(NewsRetrievalService newsRetrievalService) {
        this.newsRetrievalService = newsRetrievalService;
    }

//    public Vector<Course> getDisipline() {
//        //TODO
//        return null;
//    }

    public Vector<Teacher> getTeachers() {
        //TODO
        return null;
    }

//    public Vector<Mark> getMarks() {
//        //TODO
//        return null;
//    }

    public Vector<Request> getRequests() {
        //TODO
        return null;
    }

    public User login() {
        //TODO
        return null;
    }

    public Vector<Post> getNews() {
        //TODO
        return null;
    }

    public void subscribe() {
        //TODO
    }

    public void unsubscribe() {
        //TODO
    }
}
