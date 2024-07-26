package com.genie.journey_genie.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Checklist {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checklistID;

    @ElementCollection
    List<String> activities = new ArrayList<>();

    @ElementCollection
    List<String> places = new ArrayList<>();


    // Constructors
    public Checklist() {
    }


    // Getters and setters
    public List<String> getActivities() {
        return this.activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<String> getPlaces() {
        return this.places;
    }
    public void setPlaces(List<String> places) {
        this.places = places;
    }
    
    
}
