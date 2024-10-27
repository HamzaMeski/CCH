package com.cycloclubhorizon.controller;

import com.cycloclubhorizon.dto.GeneralResultDTO;
import com.cycloclubhorizon.service.GeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/general-results")
public class GeneralResultController {

    private final GeneralResultService generalResultService;

    @Autowired
    public GeneralResultController(GeneralResultService generalResultService) {
        this.generalResultService = generalResultService;
    }

    @GetMapping
    public ResponseEntity<List<GeneralResultDTO>> getAllGeneralResults() {
        List<GeneralResultDTO> results = generalResultService.getAllGeneralResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultDTO> getGeneralResult(@PathVariable Long competitionId, @PathVariable Long cyclistId) {
        GeneralResultDTO result = generalResultService.getGeneralResult(competitionId, cyclistId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<GeneralResultDTO> createGeneralResult(@Valid @RequestBody GeneralResultDTO generalResultDTO) {
        GeneralResultDTO createdResult = generalResultService.createGeneralResult(generalResultDTO);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @DeleteMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<Void> deleteGeneralResult(@PathVariable Long competitionId, @PathVariable Long cyclistId) {
        generalResultService.deleteGeneralResult(competitionId, cyclistId);
        return ResponseEntity.noContent().build();
    }
}
