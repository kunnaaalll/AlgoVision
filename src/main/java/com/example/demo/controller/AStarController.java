package com.example.demo.controller;

import com.example.demo.dto.StepDTO;
import com.example.demo.dto.VisualizationRequest;
import com.example.demo.service.AStarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/astar")
@CrossOrigin
public class AStarController {

    private final AStarService aStarService;

    public AStarController(AStarService aStarService) {
        this.aStarService = aStarService;
    }

    @PostMapping
    public ResponseEntity<List<StepDTO>> visualize(@RequestBody VisualizationRequest request) {
        List<StepDTO> steps = aStarService.visualize(
                request.getNodeCount(),
                request.getEdges(),
                request.getStartNode(),
                request.getGoalNode()
        );
        return ResponseEntity.ok(steps);
    }
}


