package com.genie.journey_genie.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checklistID;
    private String activities;

    public Checklist() {
    }

    public Checklist(String activities) {
        this.activities = activities;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    
}
