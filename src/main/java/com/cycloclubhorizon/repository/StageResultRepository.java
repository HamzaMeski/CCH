package com.cycloclubhorizon.repository;

import com.cycloclubhorizon.model.StageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageResultRepository extends JpaRepository<StageResult, Long> {
    Optional<StageResult> findByStageIdAndCyclistId(Long stageId, Long cyclistId);
    List<StageResult> findByCyclistIdAndStage_CompetitionId(Long cyclistId, Long competitionId);
}