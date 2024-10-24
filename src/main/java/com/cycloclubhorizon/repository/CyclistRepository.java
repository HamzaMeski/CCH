package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist, Long> {
    // Custom query methods can be added here
}