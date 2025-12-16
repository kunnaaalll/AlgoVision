package com.algovision.backend;

import com.algovision.backend.model.SortRequest;
import com.algovision.backend.model.SortStep;
import com.algovision.backend.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/algo")
public class AlgorithmController {

    private final SortingService sortingService;

    @Autowired
    public AlgorithmController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @PostMapping("/sort")
    public List<SortStep> sort(@RequestBody SortRequest request) {
        return sortingService.sort(request.getAlgorithm(), request.getData());
    }
}
