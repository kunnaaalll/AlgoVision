package com.example.demo.controller;

import com.example.demo.dto.StepDTO;
import com.example.demo.dto.VisualizationRequest;
import com.example.demo.service.MerkleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merkle")
@CrossOrigin
public class MerkleController {

    private final MerkleService merkleService;

    public MerkleController(MerkleService merkleService) {
        this.merkleService = merkleService;
    }

    @PostMapping
    public ResponseEntity<List<StepDTO>> visualize(@RequestBody VisualizationRequest request) {
        List<StepDTO> steps = merkleService.visualize(request.getDataBlocks());
        return ResponseEntity.ok(steps);
    }
}


