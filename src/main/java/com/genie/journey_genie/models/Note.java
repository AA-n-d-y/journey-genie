package com.genie.journey_genie.models;

import jakarta.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route2_id")
    private Route2 route;

    @Lob
    @Column(length = 10000)
    private String content;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route2 getRoute() {
        return route;
    }

    public void setRoute(Route2 route) {
        this.route = route;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
