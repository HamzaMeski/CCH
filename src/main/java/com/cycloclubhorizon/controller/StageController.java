package com.cycloclubhorizon.controller;

import com.cycloclubhorizon.dto.StageDTO;
import com.cycloclubhorizon.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stages")
public class StageController {

    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping
    public ResponseEntity<List<StageDTO>> getAllStages() {
        List<StageDTO> stages = stageService.getAllStages();
        return ResponseEntity.ok(stages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageDTO> getStageById(@PathVariable Long id) {
        StageDTO stage = stageService.getStageById(id);
        return ResponseEntity.ok(stage);
    }

    @PostMapping
    public ResponseEntity<StageDTO> createStage(@Valid @RequestBody StageDTO stageDTO) {
        StageDTO createdStage = stageService.createStage(stageDTO);
        return new ResponseEntity<>(createdStage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageDTO> updateStage(@PathVariable Long id, @Valid @RequestBody StageDTO stageDTO) {
        StageDTO updatedStage = stageService.updateStage(id, stageDTO);
        return ResponseEntity.ok(updatedStage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}
