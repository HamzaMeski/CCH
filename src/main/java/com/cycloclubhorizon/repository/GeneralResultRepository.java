package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, Long> {
    // Custom query methods can be added here
}
