package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BubbleSortStrategy implements SortingStrategy {

    @Override
    public List<SortStep> sort(int[] array) {
        List<SortStep> steps = new ArrayList<>();
        int n = array.length;
        // Make a copy so we don't modify the original array in place if we need it
        // later,
        // though here we are just returning steps.
        int[] arr = Arrays.copyOf(array, n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Record Compare Step
                steps.add(new SortStep("COMPARE", Arrays.asList(j, j + 1), null));

                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Record Swap Step
                    // We send the current array state or just the indices.
                    // Sending array state helps naive frontend implementation.
                    List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
                    steps.add(new SortStep("SWAP", Arrays.asList(j, j + 1), currentList));
                }
            }
        }
        return steps;
    }
}
