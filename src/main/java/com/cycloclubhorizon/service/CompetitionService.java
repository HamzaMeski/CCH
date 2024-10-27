package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.CompetitionDTO;
import com.cycloclubhorizon.model.Competition;
import com.cycloclubhorizon.repository.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<CompetitionDTO> getAllCompetitions() {
        return competitionRepository.findAll().stream()
                .map(competition -> modelMapper.map(competition, CompetitionDTO.class))
                .collect(Collectors.toList());
    }

    public CompetitionDTO getCompetitionById(Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
        return modelMapper.map(competition, CompetitionDTO.class);
    }

    public CompetitionDTO createCompetition(CompetitionDTO competitionDTO) {
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionDTO.class);
    }

    public CompetitionDTO updateCompetition(Long id, CompetitionDTO competitionDTO) {
        Competition existingCompetition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
        
        modelMapper.map(competitionDTO, existingCompetition);
        existingCompetition.setId(id); // Ensure the ID doesn't change
        
        Competition updatedCompetition = competitionRepository.save(existingCompetition);
        return modelMapper.map(updatedCompetition, CompetitionDTO.class);
    }

    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }
}
