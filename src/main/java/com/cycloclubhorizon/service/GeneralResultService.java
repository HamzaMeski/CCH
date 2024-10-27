package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.GeneralResultDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.model.Competition;
import com.cycloclubhorizon.model.GeneralResult;
import com.cycloclubhorizon.repository.CyclistRepository;
import com.cycloclubhorizon.repository.CompetitionRepository;
import com.cycloclubhorizon.repository.GeneralResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneralResultService {

    private final GeneralResultRepository generalResultRepository;
    private final CyclistRepository cyclistRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GeneralResultService(GeneralResultRepository generalResultRepository,
                                CyclistRepository cyclistRepository,
                                CompetitionRepository competitionRepository,
                                ModelMapper modelMapper) {
        this.generalResultRepository = generalResultRepository;
        this.cyclistRepository = cyclistRepository;
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<GeneralResultDTO> getAllGeneralResults() {
        return generalResultRepository.findAll().stream()
                .map(result -> modelMapper.map(result, GeneralResultDTO.class))
                .collect(Collectors.toList());
    }

    public GeneralResultDTO getGeneralResult(Long competitionId, Long cyclistId) {
        GeneralResult result = generalResultRepository.findByCompetitionIdAndCyclistId(competitionId, cyclistId)
                .orElseThrow(() -> new RuntimeException("General result not found for competition ID: " + competitionId + " and cyclist ID: " + cyclistId));
        return modelMapper.map(result, GeneralResultDTO.class);
    }

    public GeneralResultDTO createGeneralResult(GeneralResultDTO generalResultDTO) {
        Cyclist cyclist = cyclistRepository.findById(generalResultDTO.getCyclistId())
                .orElseThrow(() -> new RuntimeException("Cyclist not found with id: " + generalResultDTO.getCyclistId()));
        Competition competition = competitionRepository.findById(generalResultDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + generalResultDTO.getCompetitionId()));

        GeneralResult generalResult = modelMapper.map(generalResultDTO, GeneralResult.class);
        generalResult.setCyclist(cyclist);
        generalResult.setCompetition(competition);

        GeneralResult savedResult = generalResultRepository.save(generalResult);
        return modelMapper.map(savedResult, GeneralResultDTO.class);
    }

    public void deleteGeneralResult(Long competitionId, Long cyclistId) {
        GeneralResult result = generalResultRepository.findByCompetitionIdAndCyclistId(competitionId, cyclistId)
                .orElseThrow(() -> new RuntimeException("General result not found for competition ID: " + competitionId + " and cyclist ID: " + cyclistId));
        generalResultRepository.delete(result);
    }

    public GeneralResultDTO getWinnerForCompetition(Long competitionId) {
        GeneralResult winner = generalResultRepository.findFirstByCompetitionIdOrderByRankAsc(competitionId)
                .orElseThrow(() -> new RuntimeException("No results found for competition with id: " + competitionId));
        return modelMapper.map(winner, GeneralResultDTO.class);
    }
}