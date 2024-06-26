package com.genie.journey_genie.models;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    // Setting up the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
}
