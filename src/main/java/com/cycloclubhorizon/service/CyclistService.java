package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.CyclistDTO;
import com.cycloclubhorizon.dto.CompetitionSummaryDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.model.Competition;
import com.cycloclubhorizon.model.GeneralResult;
import com.cycloclubhorizon.repository.CyclistRepository;
import com.cycloclubhorizon.repository.GeneralResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CyclistService {

    private final CyclistRepository cyclistRepository;
    private final GeneralResultRepository generalResultRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CyclistService(CyclistRepository cyclistRepository,
                          GeneralResultRepository generalResultRepository,
                          ModelMapper modelMapper) {
        this.cyclistRepository = cyclistRepository;
        this.generalResultRepository = generalResultRepository;
        this.modelMapper = modelMapper;
    }

    public List<CyclistDTO> getAllCyclists() {
        List<Cyclist> cyclists = cyclistRepository.findAll();
        return cyclists.stream()
                .map(cyclist -> modelMapper.map(cyclist, CyclistDTO.class))
                .collect(Collectors.toList());
    }

    public CyclistDTO getCyclistById(Long id) {
        Cyclist cyclist = cyclistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cyclist not found with id: " + id));
        return modelMapper.map(cyclist, CyclistDTO.class);
    }

    public CyclistDTO createCyclist(CyclistDTO cyclistDTO) {
        Cyclist cyclist = modelMapper.map(cyclistDTO, Cyclist.class);
        Cyclist savedCyclist = cyclistRepository.save(cyclist);
        return modelMapper.map(savedCyclist, CyclistDTO.class);
    }

    public CyclistDTO updateCyclist(Long id, CyclistDTO cyclistDTO) {
        Cyclist existingCyclist = cyclistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cyclist not found with id: " + id));
        
        modelMapper.map(cyclistDTO, existingCyclist);
        existingCyclist.setId(id); // Ensure the ID doesn't change
        
        Cyclist updatedCyclist = cyclistRepository.save(existingCyclist);
        return modelMapper.map(updatedCyclist, CyclistDTO.class);
    }

    public void deleteCyclist(Long id) {
        if (!cyclistRepository.existsById(id)) {
            throw new RuntimeException("Cyclist not found with id: " + id);
        }
        cyclistRepository.deleteById(id);
    }

    public List<CompetitionSummaryDTO> getCyclistCompetitions(Long cyclistId) {
        // Verify cyclist exists
        if (!cyclistRepository.existsById(cyclistId)) {
            throw new RuntimeException("Cyclist not found with id: " + cyclistId);
        }

        // Get all general results for the cyclist
        List<GeneralResult> generalResults = generalResultRepository.findByCyclistId(cyclistId);

        // Convert to DTOs with competition information
        return generalResults.stream()
                .map(result -> {
                    CompetitionSummaryDTO dto = new CompetitionSummaryDTO();
                    Competition competition = result.getCompetition();

                    dto.setId(competition.getId());
                    dto.setName(competition.getName());
                    dto.setDate(competition.getDate());
                    dto.setLocation(competition.getLocation());
                    dto.setTotalTime(result.getTotalTime());
                    dto.setRank(result.getRank());

                    return dto;
                })
                .sorted(Comparator.comparing(CompetitionSummaryDTO::getDate).reversed())
                .collect(Collectors.toList());
    }
}
