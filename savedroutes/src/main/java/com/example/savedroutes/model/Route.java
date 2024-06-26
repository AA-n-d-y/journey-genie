package com.example.savedroutes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startPoint;
    private String endPoint;
    private String travelMode;

    @Lob
    @Column(length = 10000)
    private String routeDetails;

    private LocalDateTime creationDate;

    // Default constructor
    public Route() {
        this.creationDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Route(String startPoint, String endPoint, String travelMode, String routeDetails) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.travelMode = travelMode;
        this.routeDetails = routeDetails;
        this.creationDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
