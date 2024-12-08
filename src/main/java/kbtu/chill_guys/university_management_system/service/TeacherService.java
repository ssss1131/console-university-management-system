package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Request;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;

import java.util.Vector;

public class TeacherService {
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
}
