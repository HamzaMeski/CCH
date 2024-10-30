package com.cycloclubhorizon.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String country;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Cyclist> cyclists = new HashSet<>();  // Initialize here

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

    public Set<Cyclist> getCyclists() {
        return cyclists;
    }

    public void setCyclists(Set<Cyclist> cyclists) {
        this.cyclists = cyclists;
    }
} 