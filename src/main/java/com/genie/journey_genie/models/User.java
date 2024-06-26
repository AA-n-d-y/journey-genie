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
    private String userName;
    private String email;
    
    // Constructor
    public User(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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
        return this.userName;
    }
    public String setUserName(String userName) {
        return this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }
    public String setEmail(String email) {
        return this.email = email;
    }
    
}
