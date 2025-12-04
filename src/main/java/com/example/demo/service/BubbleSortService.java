package com.example.demo.service;

import com.example.demo.dto.StepDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BubbleSortService {

    public List<StepDTO> visualize(List<Integer> input) {
        List<StepDTO> steps = new ArrayList<>();
        if (input == null) {
            return steps;
        }
        List<Integer> arr = new ArrayList<>(input);
        int n = arr.size();
        int stepIndex = 0;

        // Initial state
        steps.add(new StepDTO(
                stepIndex++,
                "Initial array",
                new ArrayList<>(arr),
                null
        ));

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Map<String, Object> metaCompare = new HashMap<>();
                metaCompare.put("i", i);
                metaCompare.put("j", j);
                metaCompare.put("type", "compare");
                steps.add(new StepDTO(
                        stepIndex++,
                        "Compare indices " + j + " and " + (j + 1),
                        new ArrayList<>(arr),
                        metaCompare
                ));

                if (arr.get(j) > arr.get(j + 1)) {
                    int tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);

                    Map<String, Object> metaSwap = new HashMap<>();
                    metaSwap.put("i", i);
                    metaSwap.put("j", j);
                    metaSwap.put("type", "swap");
                    steps.add(new StepDTO(
                            stepIndex++,
                            "Swap indices " + j + " and " + (j + 1),
                            new ArrayList<>(arr),
                            metaSwap
                    ));
                }
            }
        }

        steps.add(new StepDTO(
                stepIndex,
                "Sorted array",
                new ArrayList<>(arr),
                null
        ));

        return steps;
    }
}


