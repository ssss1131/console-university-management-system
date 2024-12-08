package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.User;


public class Complaint extends Post {
    private User complainantUser; //кому жалуется
    private Status status;
    
    public User getComplainantUser() {
        return this.complainantUser;
    }

    public void setComplainantUser(User complainantUser) {
        this.complainantUser = complainantUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
