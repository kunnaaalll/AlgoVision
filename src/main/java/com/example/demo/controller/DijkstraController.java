package com.example.demo.controller;

import com.example.demo.dto.StepDTO;
import com.example.demo.dto.VisualizationRequest;
import com.example.demo.service.DijkstraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dijkstra")
@CrossOrigin
public class DijkstraController {

    private final DijkstraService dijkstraService;

    public DijkstraController(DijkstraService dijkstraService) {
        this.dijkstraService = dijkstraService;
    }

    @PostMapping
    public ResponseEntity<List<StepDTO>> visualize(@RequestBody VisualizationRequest request) {
        List<StepDTO> steps = dijkstraService.visualize(
                request.getNodeCount(),
                request.getEdges(),
                request.getStartNode()
        );
        return ResponseEntity.ok(steps);
    }
}


