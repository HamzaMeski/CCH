package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.StageResultDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.model.Stage;
import com.cycloclubhorizon.model.StageResult;
import com.cycloclubhorizon.model.GeneralResult;
import com.cycloclubhorizon.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StageResultService {

    private final StageResultRepository stageResultRepository;
    private final CyclistRepository cyclistRepository;
    private final StageRepository stageRepository;
    private final GeneralResultRepository generalResultRepository;
    private final ModelMapper modelMapper;
    private final RankingService rankingService;

    @Autowired
    public StageResultService(StageResultRepository stageResultRepository,
                            CyclistRepository cyclistRepository,
                            StageRepository stageRepository,
                            GeneralResultRepository generalResultRepository,
                            ModelMapper modelMapper,
                            RankingService rankingService) {
        this.stageResultRepository = stageResultRepository;
        this.cyclistRepository = cyclistRepository;
        this.stageRepository = stageRepository;
        this.generalResultRepository = generalResultRepository;
        this.modelMapper = modelMapper;
        this.rankingService = rankingService;
    }

    public List<StageResultDTO> getAllStageResults() {
        return stageResultRepository.findAll().stream()
                .map(result -> modelMapper.map(result, StageResultDTO.class))
                .collect(Collectors.toList());
    }

    public StageResultDTO getStageResult(Long stageId, Long cyclistId) {
        StageResult result = stageResultRepository.findByStageIdAndCyclistId(stageId, cyclistId)
                .orElseThrow(() -> new RuntimeException("Stage result not found for stage ID: " + stageId + " and cyclist ID: " + cyclistId));
        return modelMapper.map(result, StageResultDTO.class);
    }

    public StageResultDTO createStageResult(StageResultDTO stageResultDTO) {
        Cyclist cyclist = cyclistRepository.findById(stageResultDTO.getCyclistId())
                .orElseThrow(() -> new RuntimeException("Cyclist not found with id: " + stageResultDTO.getCyclistId()));
        Stage stage = stageRepository.findById(stageResultDTO.getStageId())
                .orElseThrow(() -> new RuntimeException("Stage not found with id: " + stageResultDTO.getStageId()));

        StageResult stageResult = modelMapper.map(stageResultDTO, StageResult.class);
        stageResult.setCyclist(cyclist);
        stageResult.setStage(stage);

        StageResult savedResult = stageResultRepository.save(stageResult);

        // Create or update GeneralResult
        GeneralResult generalResult = generalResultRepository
                .findByCompetitionIdAndCyclistId(stage.getCompetition().getId(), cyclist.getId())
                .orElse(new GeneralResult());

        if (generalResult.getId() == null) {
            generalResult.setCyclist(cyclist);
            generalResult.setCompetition(stage.getCompetition());
            generalResult.setTotalTime(0L);
            generalResult.setRank(0);
            generalResultRepository.save(generalResult);
        }

        // Update rankings for the competition
        rankingService.updateRankingsForCompetition(stage.getCompetition().getId());

        return modelMapper.map(savedResult, StageResultDTO.class);
    }

    public void deleteStageResult(Long stageId, Long cyclistId) {
        StageResult result = stageResultRepository.findByStageIdAndCyclistId(stageId, cyclistId)
                .orElseThrow(() -> new RuntimeException("Stage result not found for stage ID: " + stageId + " and cyclist ID: " + cyclistId));
        
        Stage stage = result.getStage();
        stageResultRepository.delete(result);
        
        // Update rankings after deletion
        rankingService.updateRankingsForCompetition(stage.getCompetition().getId());
    }
}