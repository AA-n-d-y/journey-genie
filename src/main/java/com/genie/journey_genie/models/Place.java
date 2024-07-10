// Java file for the Place class

package com.genie.journey_genie.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;

@Entity
public class Place {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeID;

    // Attributes
    private String placeName;
    private String location;
    
    // Constructors
    public Place() {

    }
    
    public Place(String placeName, String location) {
        this.placeName = placeName;
        this.location = location;
    }


    // Getters and setters
    public int getPlaceID() {
        return this.placeID;
    }
    
    public String getPlaceName() {
        return this.placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
