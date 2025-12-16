package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsertionSortStrategy implements SortingStrategy {

    @Override
    public List<SortStep> sort(int[] array) {
        List<SortStep> steps = new ArrayList<>();
        int n = array.length;
        int[] arr = Arrays.copyOf(array, n);

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Record key selection if desired, or just compares in loop

            while (j >= 0 && arr[j] > key) {
                // Compare
                steps.add(new SortStep("COMPARE", Arrays.asList(j, i), null)); // comparing with key initially at i

                arr[j + 1] = arr[j];
                j = j - 1;

                // Record overwrite/move
                List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
                steps.add(new SortStep("SWAP", Arrays.asList(j + 1, j + 2), currentList)); // simulating move as swap
                                                                                           // visual for now
            }
            arr[j + 1] = key;

            // Final placement
            List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
            steps.add(new SortStep("SWAP", Arrays.asList(j + 1, i), currentList));
        }
        return steps;
    }
}
