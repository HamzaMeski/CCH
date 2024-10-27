package com.cycloclubhorizon.controller;

import com.cycloclubhorizon.dto.StageResultDTO;
import com.cycloclubhorizon.service.StageResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stage-results")
public class StageResultController {

    private final StageResultService stageResultService;

    @Autowired
    public StageResultController(StageResultService stageResultService) {
        this.stageResultService = stageResultService;
    }

    @GetMapping
    public ResponseEntity<List<StageResultDTO>> getAllStageResults() {
        List<StageResultDTO> results = stageResultService.getAllStageResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<StageResultDTO> getStageResult(@PathVariable Long stageId, @PathVariable Long cyclistId) {
        StageResultDTO result = stageResultService.getStageResult(stageId, cyclistId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<StageResultDTO> createStageResult(@Valid @RequestBody StageResultDTO stageResultDTO) {
        StageResultDTO createdResult = stageResultService.createStageResult(stageResultDTO);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @DeleteMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<Void> deleteStageResult(@PathVariable Long stageId, @PathVariable Long cyclistId) {
        stageResultService.deleteStageResult(stageId, cyclistId);
        return ResponseEntity.noContent().build();
    }
}
