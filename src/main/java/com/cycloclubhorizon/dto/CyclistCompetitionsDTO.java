package com.cycloclubhorizon.dto;

import java.util.List;

public class CyclistCompetitionsDTO {
    private Long cyclistId;
    private String cyclistName;
    private List<CompetitionSummaryDTO> competitions;

    // Constructor
    public CyclistCompetitionsDTO(Long cyclistId, String cyclistName, List<CompetitionSummaryDTO> competitions) {
        this.cyclistId = cyclistId;
        this.cyclistName = cyclistName;
        this.competitions = competitions;
    }

    // Getters and Setters
    public Long getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Long cyclistId) {
        this.cyclistId = cyclistId;
    }

    public String getCyclistName() {
        return cyclistName;
    }

    public void setCyclistName(String cyclistName) {
        this.cyclistName = cyclistName;
    }

    public List<CompetitionSummaryDTO> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionSummaryDTO> competitions) {
        this.competitions = competitions;
    }
} 