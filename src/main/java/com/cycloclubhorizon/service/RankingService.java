package com.cycloclubhorizon.service;

import com.cycloclubhorizon.model.GeneralResult;
import com.cycloclubhorizon.repository.GeneralResultRepository;
import com.cycloclubhorizon.repository.StageResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class RankingService {

    private final GeneralResultRepository generalResultRepository;
    private final StageResultRepository stageResultRepository;

    @Autowired
    public RankingService(GeneralResultRepository generalResultRepository,
                          StageResultRepository stageResultRepository) {
        this.generalResultRepository = generalResultRepository;
        this.stageResultRepository = stageResultRepository;
    }

    public void updateRankingsForCompetition(Long competitionId) {
        List<GeneralResult> results = generalResultRepository.findByCompetitionId(competitionId);

        // Calculate total time for each cyclist
        for (GeneralResult result : results) {
            Long totalTime = calculateTotalTimeForCyclist(competitionId, result.getCyclist().getId());
            result.setTotalTime(totalTime);
        }
        
        // Sort results by total time
        results.sort(Comparator.comparing(GeneralResult::getTotalTime));
        
        // Update ranks
        for (int i = 0; i < results.size(); i++) {
            results.get(i).setRank(i + 1);
        }

        // Save updated results
        generalResultRepository.saveAll(results);
    }

    private Long calculateTotalTimeForCyclist(Long competitionId, Long cyclistId) {
        return stageResultRepository.findByCyclistIdAndStage_CompetitionId(cyclistId, competitionId)
                .stream()
                .mapToLong(stageResult -> stageResult.getTime())
                .sum();
    }
}