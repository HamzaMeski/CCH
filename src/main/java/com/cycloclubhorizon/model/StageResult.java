package com.cycloclubhorizon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stage_results")
public class StageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cyclist_id", nullable = false)
    private Cyclist cyclist;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    private Long time;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
