// Java file for the User class

package com.genie.journey_genie.models;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    // Setting up the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   
    
    // Attributes
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin = false;
    
    // Constructor
    public User(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    // Getters and setters
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return this.username;
    }
    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getUserType() {
        return this.isAdmin;
    }
    public void setUserType(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
