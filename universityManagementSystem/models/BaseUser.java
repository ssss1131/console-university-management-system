package universityManagementSystem.models;


import universityManagementSystem.Vector;

import java.util.UUID;

/**
* @generated
*/
public class BaseUser {
    
    /**
    * @generated
    */
    private UUID id;
    
    /**
    * @generated
    */
    private String email;
    
    /**
    * @generated
    */
    private String firstName;
    
    /**
    * @generated
    */
    private String lastName;
    
    /**
    * @generated
    */
    private Vector<Post> notifications;
    
    
    
    /**
    * @generated
    */
    private UUID getId() {
        return this.id;
    }
    
    /**
    * @generated
    */
    private UUID setId(UUID id) {
        this.id = id;
    }
    
    /**
    * @generated
    */
    private String getEmail() {
        return this.email;
    }
    
    /**
    * @generated
    */
    private String setEmail(String email) {
        this.email = email;
    }
    
    /**
    * @generated
    */
    private String getFirstName() {
        return this.firstName;
    }
    
    /**
    * @generated
    */
    private String setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
    * @generated
    */
    private String getLastName() {
        return this.lastName;
    }
    
    /**
    * @generated
    */
    private String setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
    * @generated
    */
    private Vector<Post> getNotifications() {
        return this.notifications;
    }
    
    /**
    * @generated
    */
    private Vector<Post> setNotifications(Vector<Post> notifications) {
        this.notifications = notifications;
    }
    
}
