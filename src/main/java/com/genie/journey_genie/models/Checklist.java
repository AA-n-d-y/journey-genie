package com.genie.journey_genie.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checklistID;
    @ElementCollection
    private List<String> activities = new ArrayList<>();
    //private List<String> place;

    public Checklist() {
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    // public Checklist(String activities) {
    //     this.activities = activities;
    // }

    
    
    
}
