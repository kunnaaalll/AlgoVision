package com.example.demo.dto;

import com.example.demo.model.AlgorithmType;

import java.util.List;

/**
 * Generic request payload that the frontend can send to ask
 * for a visualization of an algorithm.
 */
public class VisualizationRequest {

    private AlgorithmType algorithm;

    // For sorting algorithms
    private List<Integer> array;

    // For graph algorithms – number of nodes, edges, start, end, etc.
    private int nodeCount;
    private List<int[]> edges; // each edge: [from, to, weight]
    private int startNode;
    private int goalNode; // used by A*

    // For Merkle tree – list of data blocks (strings)
    private List<String> dataBlocks;

    public AlgorithmType getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(AlgorithmType algorithm) {
        this.algorithm = algorithm;
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public List<int[]> getEdges() {
        return edges;
    }

    public void setEdges(List<int[]> edges) {
        this.edges = edges;
    }

    public int getStartNode() {
        return startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getGoalNode() {
        return goalNode;
    }

    public void setGoalNode(int goalNode) {
        this.goalNode = goalNode;
    }

    public List<String> getDataBlocks() {
        return dataBlocks;
    }

    public void setDataBlocks(List<String> dataBlocks) {
        this.dataBlocks = dataBlocks;
    }
}


