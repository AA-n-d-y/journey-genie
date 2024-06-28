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
    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }

    public String getUserName() {
        return this.username;
    }
    public String setUserName(String username) {
        return this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    public String setPassword(String password) {
        return this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    public String setEmail(String email) {
        return this.email = email;
    }
    
}
