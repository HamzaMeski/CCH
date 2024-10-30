package com.cycloclubhorizon.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class TeamCreateDTO {
    @NotBlank(message = "Team name is required")
    private String name;

    private String country;

    private List<Long> cyclistIds;

    // Default constructor
    public TeamCreateDTO() {
    }

    // Getters and Setters
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

    public List<Long> getCyclistIds() {
        return cyclistIds;
    }

    public void setCyclistIds(List<Long> cyclistIds) {
        this.cyclistIds = cyclistIds;
    }
}