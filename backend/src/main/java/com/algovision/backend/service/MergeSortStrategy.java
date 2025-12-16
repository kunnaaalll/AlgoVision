package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MergeSortStrategy implements SortingStrategy {

    @Override
    public List<SortStep> sort(int[] array) {
        List<SortStep> steps = new ArrayList<>();
        int n = array.length;
        int[] arr = Arrays.copyOf(array, n);

        mergeSort(arr, 0, n - 1, steps);

        return steps;
    }

    private void mergeSort(int[] arr, int l, int r, List<SortStep> steps) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m, steps);
            mergeSort(arr, m + 1, r, steps);

            merge(arr, l, m, r, steps);
        }
    }

    private void merge(int[] arr, int l, int m, int r, List<SortStep> steps) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            // Compare
            // steps.add(new SortStep("COMPARE", Arrays.asList(l + i, m + 1 + j), null));
            // Indices is tricky with aux arrays, so we just snapshot updates.

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            // Snapshot update
            List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
            steps.add(new SortStep("OVERWRITE", Arrays.asList(k), currentList));
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
            steps.add(new SortStep("OVERWRITE", Arrays.asList(k), currentList));
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            List<Integer> currentList = Arrays.stream(arr).boxed().collect(Collectors.toList());
            steps.add(new SortStep("OVERWRITE", Arrays.asList(k), currentList));
            j++;
            k++;
        }
    }
}
