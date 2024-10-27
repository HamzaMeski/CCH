package com.cycloclubhorizon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GeneralResultDTO {
    private Long id;

    @NotNull(message = "Cyclist ID is required")
    private Long cyclistId;

    @NotNull(message = "Competition ID is required")
    private Long competitionId;

    @NotNull(message = "Total time is required")
    @Positive(message = "Total time must be positive")
    private Long totalTime;

    private Integer rank;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Long cyclistId) {
        this.cyclistId = cyclistId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
