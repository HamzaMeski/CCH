package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.StageResultDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.model.Stage;
import com.cycloclubhorizon.model.StageResult;
import com.cycloclubhorizon.repository.CyclistRepository;
import com.cycloclubhorizon.repository.StageRepository;
import com.cycloclubhorizon.repository.StageResultRepository;
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
    private final ModelMapper modelMapper;

    @Autowired
    public StageResultService(StageResultRepository stageResultRepository,
                              CyclistRepository cyclistRepository,
                              StageRepository stageRepository,
                              ModelMapper modelMapper) {
        this.stageResultRepository = stageResultRepository;
        this.cyclistRepository = cyclistRepository;
        this.stageRepository = stageRepository;
        this.modelMapper = modelMapper;
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
        return modelMapper.map(savedResult, StageResultDTO.class);
    }

    public void deleteStageResult(Long stageId, Long cyclistId) {
        StageResult result = stageResultRepository.findByStageIdAndCyclistId(stageId, cyclistId)
                .orElseThrow(() -> new RuntimeException("Stage result not found for stage ID: " + stageId + " and cyclist ID: " + cyclistId));
        stageResultRepository.delete(result);
    }
}
