// Java file for the User class

package com.genie.journey_genie.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    // Attributes
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    private Preferences preferences;
    

    // Constructors
    public User() {

    }
    
    public User(String firstName, String lastName, String username, String password, String email, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }


    // Getters and setters
    public int getUserID() {
        return this.userID;
    }
    
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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Preferences getPreferences() {
        return preferences;
    }
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
    
}
