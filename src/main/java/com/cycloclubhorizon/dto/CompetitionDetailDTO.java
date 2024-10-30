package com.cycloclubhorizon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class CompetitionDetailDTO {
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String location;
    private List<StageDTO> stages;
    private List<GeneralResultDTO> generalResults;

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

    public List<StageDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDTO> stages) {
        this.stages = stages;
    }

    public List<GeneralResultDTO> getGeneralResults() {
        return generalResults;
    }

    public void setGeneralResults(List<GeneralResultDTO> generalResults) {
        this.generalResults = generalResults;
    }
} 