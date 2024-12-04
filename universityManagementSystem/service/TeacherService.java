package universityManagementSystem.service;


import universityManagementSystem.Mark;
import universityManagementSystem.Request;
import universityManagementSystem.Teacher;
import universityManagementSystem.Vector;
import universityManagementSystem.controller.ServiceFactoryImpl;
import universityManagementSystem.repository.UserRepository;

/**
* @generated
*/
public class TeacherService {
    
    /**
    * @generated
    */
    private UserRepository userRepository;
    
    
    /**
    * @generated
    */
    private ServiceFactoryImpl serviceFactoryImpl;
    
    
    /**
    * @generated
    */
    private UserRepository getUserRepository() {
        return this.userRepository;
    }
    
    /**
    * @generated
    */
    private UserRepository setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public Vector<Teacher> getTeachers() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public Vector<Mark> getMarks() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public Vector<Request> getRequests() {
        //TODO
        return null;
    }
    
    
}
