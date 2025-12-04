package com.example.demo.controller;

import com.example.demo.dto.StepDTO;
import com.example.demo.dto.VisualizationRequest;
import com.example.demo.service.BubbleSortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bubble-sort")
@CrossOrigin
public class BubbleSortController {

    private final BubbleSortService bubbleSortService;

    public BubbleSortController(BubbleSortService bubbleSortService) {
        this.bubbleSortService = bubbleSortService;
    }

    @PostMapping
    public ResponseEntity<List<StepDTO>> visualize(@RequestBody VisualizationRequest request) {
        List<StepDTO> steps = bubbleSortService.visualize(request.getArray());
        return ResponseEntity.ok(steps);
    }
}


