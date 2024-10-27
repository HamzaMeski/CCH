package com.cycloclubhorizon.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String location;

    private Double distance;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private Set<Stage> stages;

    @OneToMany(mappedBy = "competition")
    private Set<GeneralResult> generalResults;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public Set<GeneralResult> getGeneralResults() {
        return generalResults;
    }

    public void setGeneralResults(Set<GeneralResult> generalResults) {
        this.generalResults = generalResults;
    }
}
