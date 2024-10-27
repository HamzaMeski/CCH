package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.StageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageResultRepository extends JpaRepository<StageResult, Long> {
    // Custom query methods can be added here
}
