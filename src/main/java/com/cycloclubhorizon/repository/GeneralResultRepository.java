package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, Long> {
    Optional<GeneralResult> findByCompetitionIdAndCyclistId(Long competitionId, Long cyclistId);
    List<GeneralResult> findByCompetitionId(Long competitionId);
    Optional<GeneralResult> findFirstByCompetitionIdOrderByRankAsc(Long competitionId);
    List<GeneralResult> findByCyclistId(Long cyclistId);
}