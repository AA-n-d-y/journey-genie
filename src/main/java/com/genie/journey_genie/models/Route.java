package com.genie.journey_genie.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text[]")
    @Type(value = com.genie.journey_genie.models.CustomStringArrayType.class)
    private String[] coords;

    @Column(columnDefinition = "text[]")
    @Type(value = com.genie.journey_genie.models.CustomStringArrayType.class)
    private String[] points;

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
    public Route(String[] coords, String[] points, String travelMode, String routeDetails) {
        this.coords = coords;
        this.points = points;
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

    public String getStartCoords() {
        return coords[0];
    }

    public void setStartCoords(String startCoords) {
        this.coords[0] = startCoords;
    }

    public String getEndCoords() { return coords[coords.length-1]; }

    public void setEndCoords(String endCoords) {
        this.coords[coords.length-1] = endCoords;
    }

    public String getStartPoint() {
        return points[0];
    }

    public void setStartPoint(String startPoint) {
        this.points[0] = startPoint;
    }

    public String getEndPoint() {
        return points[points.length-1];
    }

    public void setEndPoint(String endPoint) {
        this.points[points.length-1] = endPoint;
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

    public String[] getCoords() { return coords; }

    public void setCoords(String[] coords) { this.coords = coords; }

    public String[] getPoints() { return points; }

    public void setPoints(String[] points) { this.points = points; }
}
