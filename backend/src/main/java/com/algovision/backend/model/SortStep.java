package com.algovision.backend.model;

import java.util.List;

public class SortStep {
    private String type; // "COMPARE", "SWAP", "OVERWRITE"
    private List<Integer> indices; // Indices involved in the operation
    private List<Integer> currentArray; // Optional: Setup for easier debugging, optimize later if needed

    public SortStep(String type, List<Integer> indices, List<Integer> currentArray) {
        this.type = type;
        this.indices = indices;
        this.currentArray = currentArray;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public List<Integer> getCurrentArray() {
        return currentArray;
    }

    public void setCurrentArray(List<Integer> currentArray) {
        this.currentArray = currentArray;
    }
}
