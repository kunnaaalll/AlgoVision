package com.algovision.backend.service;

import com.algovision.backend.model.SortStep;
import java.util.List;

public interface SortingStrategy {
    List<SortStep> sort(int[] array);
}
