package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectionSortStrategy implements SortingStrategy {

    @Override
    public List<SortStep> sort(int[] array) {
        List<SortStep> steps = new ArrayList<>();
        int n = array.length;
        int[] arr = Arrays.copyOf(array, n);

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                // Record Compare
                steps.add(new SortStep("COMPARE", Arrays.asList(minIdx, j), null));
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                // Swap
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;

                // Record Swap with snapshot
                List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
                steps.add(new SortStep("SWAP", Arrays.asList(i, minIdx), currentList));
            }
        }
        return steps;
    }
}
