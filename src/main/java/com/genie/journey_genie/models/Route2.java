package com.genie.journey_genie.models;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Route2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)[]")
    @Type(StringArrayType.class)
    private String[] coords;

    @Column(columnDefinition = "varchar(255)[]")
    @Type(StringArrayType.class)
    private String[] points;

    private String travelMode;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Default constructor
    public Route2() {
        this.creationDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Route2(String[] coords, String[] points, String travelMode, User user) {
        this.coords = coords;
        this.points = points;
        this.travelMode = travelMode;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFormattedCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy, hh:mm a");
        return this.creationDate.format(formatter);
    }
}
