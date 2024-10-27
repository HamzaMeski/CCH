package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.StageDTO;
import com.cycloclubhorizon.model.Competition;
import com.cycloclubhorizon.model.Stage;
import com.cycloclubhorizon.repository.CompetitionRepository;
import com.cycloclubhorizon.repository.StageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StageService {

    private final StageRepository stageRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StageService(StageRepository stageRepository, CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.stageRepository = stageRepository;
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<StageDTO> getAllStages() {
        return stageRepository.findAll().stream()
                .map(stage -> modelMapper.map(stage, StageDTO.class))
                .collect(Collectors.toList());
    }

    public StageDTO getStageById(Long id) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found with id: " + id));
        return modelMapper.map(stage, StageDTO.class);
    }

    public StageDTO createStage(StageDTO stageDTO) {
        Competition competition = competitionRepository.findById(stageDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + stageDTO.getCompetitionId()));

        Stage stage = modelMapper.map(stageDTO, Stage.class);
        stage.setCompetition(competition);

        Stage savedStage = stageRepository.save(stage);
        return modelMapper.map(savedStage, StageDTO.class);
    }

    public StageDTO updateStage(Long id, StageDTO stageDTO) {
        Stage existingStage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found with id: " + id));

        Competition competition = competitionRepository.findById(stageDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + stageDTO.getCompetitionId()));

        modelMapper.map(stageDTO, existingStage);
        existingStage.setId(id);
        existingStage.setCompetition(competition);

        Stage updatedStage = stageRepository.save(existingStage);
        return modelMapper.map(updatedStage, StageDTO.class);
    }

    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }
}
