package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    // Custom query methods can be added here
}
