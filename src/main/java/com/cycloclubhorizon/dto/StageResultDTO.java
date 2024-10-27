package com.cycloclubhorizon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StageResultDTO {
    private Long id;

    @NotNull(message = "Cyclist ID is required")
    private Long cyclistId;

    @NotNull(message = "Stage ID is required")
    private Long stageId;

    @NotNull(message = "Time is required")
    @Positive(message = "Time must be positive")
    private Long time;

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

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
