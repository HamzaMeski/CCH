package com.cycloclubhorizon.service;

import com.cycloclubhorizon.dto.CyclistDTO;
import com.cycloclubhorizon.model.Cyclist;
import com.cycloclubhorizon.repository.CyclistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CyclistService {

    private final CyclistRepository cyclistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CyclistService(CyclistRepository cyclistRepository, ModelMapper modelMapper) {
        this.cyclistRepository = cyclistRepository;
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
}
