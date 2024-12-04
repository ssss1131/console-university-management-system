package universityManagementSystem.services;


import universityManagementSystem.repository.UserRepository;

/**
* @generated
*/
public class AuthService {
    
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
    public User login() {
        //TODO
        return null;
    }
    
    /**
    * @generated
    */
    public String hashPassword() {
        //TODO
        return "";
    }
    
    
}
