package com.cycloclubhorizon.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cyclists")
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String nationality;

    @OneToMany(mappedBy = "cyclist")
    private Set<GeneralResult> generalResults;

    @OneToMany(mappedBy = "cyclist")
    private Set<StageResult> stageResults;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<GeneralResult> getGeneralResults() {
        return generalResults;
    }

    public void setGeneralResults(Set<GeneralResult> generalResults) {
        this.generalResults = generalResults;
    }

    public Set<StageResult> getStageResults() {
        return stageResults;
    }

    public void setStageResults(Set<StageResult> stageResults) {
        this.stageResults = stageResults;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
