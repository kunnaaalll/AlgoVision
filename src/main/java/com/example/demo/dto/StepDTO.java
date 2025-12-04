package com.example.demo.dto;

import java.util.List;
import java.util.Map;

public class StepDTO {

    private int stepIndex;
    private String description;

    // For sorting algorithms – the current array state
    private List<Integer> arrayState;

    // For graph algorithms – arbitrary state like distances, open/closed sets, etc.
    private Map<String, Object> meta;

    public StepDTO() {
    }

    public StepDTO(int stepIndex, String description, List<Integer> arrayState, Map<String, Object> meta) {
        this.stepIndex = stepIndex;
        this.description = description;
        this.arrayState = arrayState;
        this.meta = meta;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getArrayState() {
        return arrayState;
    }

    public void setArrayState(List<Integer> arrayState) {
        this.arrayState = arrayState;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}


