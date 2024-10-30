package com.cycloclubhorizon.dto;

import java.util.List;

public class TeamViewDTO {
    private Long id;
    private String name;
    private String country;
    private List<CyclistDTO> cyclists;

    // Default constructor
    public TeamViewDTO() {
    }

    // Getters and Setters
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CyclistDTO> getCyclists() {
        return cyclists;
    }

    public void setCyclists(List<CyclistDTO> cyclists) {
        this.cyclists = cyclists;
    }
}