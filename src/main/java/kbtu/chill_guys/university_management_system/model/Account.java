package main.java.kbtu.chill_guys.university_management_system.model;


import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Role;

import java.io.Serializable;

public class Account implements Serializable {

    private String login;
    private String password;
    private Role role;

}
