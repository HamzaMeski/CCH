package com.cycloclubhorizon.controller;

import com.cycloclubhorizon.dto.TeamCreateDTO;
import com.cycloclubhorizon.dto.TeamViewDTO;
import com.cycloclubhorizon.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamViewDTO>> getAllTeams() {
        List<TeamViewDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamViewDTO> getTeamById(@PathVariable Long id) {
        TeamViewDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @PostMapping
    public ResponseEntity<TeamViewDTO> createTeam(@Valid @RequestBody TeamCreateDTO teamCreateDTO) {
        TeamViewDTO createdTeam = teamService.createTeam(teamCreateDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamViewDTO> updateTeam(@PathVariable Long id,
                                                  @Valid @RequestBody TeamCreateDTO teamCreateDTO) {
        TeamViewDTO updatedTeam = teamService.updateTeam(id, teamCreateDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}