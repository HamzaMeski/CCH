package com.cycloclubhorizon.controller;

import com.cycloclubhorizon.dto.CyclistDTO;
import com.cycloclubhorizon.dto.CompetitionSummaryDTO;
import com.cycloclubhorizon.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;

    @Autowired
    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @GetMapping
    public ResponseEntity<List<CyclistDTO>> getAllCyclists() {
        List<CyclistDTO> cyclists = cyclistService.getAllCyclists();
        return ResponseEntity.ok(cyclists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistDTO> getCyclistById(@PathVariable Long id) {
        CyclistDTO cyclist = cyclistService.getCyclistById(id);
        return ResponseEntity.ok(cyclist);
    }

    @PostMapping
    public ResponseEntity<CyclistDTO> createCyclist(@RequestBody CyclistDTO cyclistDTO) {
        CyclistDTO createdCyclist = cyclistService.createCyclist(cyclistDTO);
        return new ResponseEntity<>(createdCyclist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistDTO> updateCyclist(@PathVariable Long id, @RequestBody CyclistDTO cyclistDTO) {
        CyclistDTO updatedCyclist = cyclistService.updateCyclist(id, cyclistDTO);
        return ResponseEntity.ok(updatedCyclist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyclist(@PathVariable Long id) {
        cyclistService.deleteCyclist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/competitions")
    public ResponseEntity<List<CompetitionSummaryDTO>> getCyclistCompetitions(@PathVariable Long id) {
        try {
            List<CompetitionSummaryDTO> competitions = cyclistService.getCyclistCompetitions(id);
            return ResponseEntity.ok(competitions);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
