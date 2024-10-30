package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.CyclistDTO;
import com.cycloclubhorizon.dto.TeamCreateDTO;
import com.cycloclubhorizon.dto.TeamViewDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.model.Team;
import com.cycloclubhorizon.repository.CyclistRepository;
import com.cycloclubhorizon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    private final CyclistRepository cyclistRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, CyclistRepository cyclistRepository) {
        this.teamRepository = teamRepository;
        this.cyclistRepository = cyclistRepository;
    }

    public List<TeamViewDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
    }

    public TeamViewDTO getTeamById(Long id) {
        final Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
        return convertToViewDTO(team);
    }

    public TeamViewDTO createTeam(TeamCreateDTO createDTO) {
        // Create new team
        Team team = createTeamEntity(createDTO);
        team.setCyclists(new HashSet<>());  // Initialize cyclists set
        final Team savedTeam = teamRepository.save(team);

        // Add cyclists if any
        if (createDTO.getCyclistIds() != null && !createDTO.getCyclistIds().isEmpty()) {
            List<Cyclist> cyclists = cyclistRepository.findAllById(createDTO.getCyclistIds());
            cyclists.forEach(cyclist -> cyclist.setTeam(savedTeam));
            cyclistRepository.saveAll(cyclists);
        }

        return convertToViewDTO(savedTeam);
    }

    public TeamViewDTO updateTeam(Long id, TeamCreateDTO updateDTO) {
        // Check if team exists
        final Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        // Update basic info
        existingTeam.setName(updateDTO.getName());
        existingTeam.setCountry(updateDTO.getCountry());

        // Update cyclist associations
        // First, remove all existing associations
        List<Cyclist> currentCyclists = (List<Cyclist>) existingTeam.getCyclists();
        currentCyclists.forEach(cyclist -> cyclist.setTeam(null));
        cyclistRepository.saveAll(currentCyclists);

        // Then, set new associations
        if (updateDTO.getCyclistIds() != null && !updateDTO.getCyclistIds().isEmpty()) {
            List<Cyclist> newCyclists = cyclistRepository.findAllById(updateDTO.getCyclistIds());
            newCyclists.forEach(cyclist -> cyclist.setTeam(existingTeam));
            cyclistRepository.saveAll(newCyclists);
        }

        final Team updatedTeam = teamRepository.save(existingTeam);
        return convertToViewDTO(updatedTeam);
    }

    public void deleteTeam(Long id) {
        final Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        // Remove team association from cyclists
        List<Cyclist> cyclists = (List<Cyclist>) team.getCyclists();
        cyclists.forEach(cyclist -> cyclist.setTeam(null));
        cyclistRepository.saveAll(cyclists);

        // Delete the team
        teamRepository.delete(team);
    }

    // Helper method to create Team entity from DTO
    private Team createTeamEntity(TeamCreateDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setCountry(dto.getCountry());
        return team;
    }

    // Helper method to convert Team entity to TeamViewDTO
    private TeamViewDTO convertToViewDTO(Team team) {
        TeamViewDTO dto = new TeamViewDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCountry(team.getCountry());

        // Handle null cyclists set
        Set<Cyclist> cyclists = team.getCyclists();
        if (cyclists != null) {
            List<CyclistDTO> cyclistDTOs = cyclists.stream()
                    .map(this::convertToCyclistDTO)
                    .collect(Collectors.toList());
            dto.setCyclists(cyclistDTOs);
        } else {
            dto.setCyclists(new ArrayList<>());  // Empty list if null
        }

        return dto;
    }

    // Helper method to convert Cyclist entity to CyclistDTO
    private CyclistDTO convertToCyclistDTO(Cyclist cyclist) {
        CyclistDTO dto = new CyclistDTO();
        dto.setId(cyclist.getId());
        dto.setFirstName(cyclist.getFirstName());
        dto.setLastName(cyclist.getLastName());
        dto.setNationality(cyclist.getNationality());
        dto.setDateOfBirth(cyclist.getDateOfBirth());
        return dto;
    }
}