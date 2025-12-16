package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SortingService {

    private final Map<String, SortingStrategy> strategies = new HashMap<>();

    @Autowired
    public SortingService(
            BubbleSortStrategy bubbleSort,
            SelectionSortStrategy selectionSort,
            InsertionSortStrategy insertionSort,
            MergeSortStrategy mergeSort) {
        strategies.put("BUBBLE", bubbleSort);
        strategies.put("SELECTION", selectionSort);
        strategies.put("INSERTION", insertionSort);
        strategies.put("MERGE", mergeSort);
    }

    public List<SortStep> sort(String algorithm, int[] array) {
        SortingStrategy strategy = strategies.get(algorithm.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return strategy.sort(array);
    }
}
