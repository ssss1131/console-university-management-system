package universityManagementSystem.models.academic;


import universityManagementSystem.Vector;

/**
* @generated
*/
public class News {
    
    /**
    * @generated
    */
    private String topic;
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.Post> comments;
    
    
    
    /**
    * @generated
    */
    private String getTopic() {
        return this.topic;
    }
    
    /**
    * @generated
    */
    private String setTopic(String topic) {
        this.topic = topic;
    }
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.Post> getComments() {
        return this.comments;
    }
    
    /**
    * @generated
    */
    private Vector<universityManagementSystem.Post> setComments(Vector<Post> comments) {
        this.comments = comments;
    }
    
}
