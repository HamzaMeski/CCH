package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    // Custom query methods can be added here
}
