package com.example.demo.service;

import com.example.demo.dto.StepDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AStarService {

    /**
     * Simple A* on a generic weighted graph.
     * Heuristic here is zero (so it behaves like Dijkstra) unless you adapt it –
     * but we still emit the typical A* open/closed sets for visualization.
     */
    public List<StepDTO> visualize(int nodeCount, List<int[]> edges, int startNode, int goalNode) {
        List<StepDTO> steps = new ArrayList<>();
        if (nodeCount <= 0 || startNode < 0 || startNode >= nodeCount || goalNode < 0 || goalNode >= nodeCount) {
            return steps;
        }

        List<List<int[]>> graph = buildGraph(nodeCount, edges);
        int[] gScore = new int[nodeCount];
        int[] fScore = new int[nodeCount];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        Arrays.fill(fScore, Integer.MAX_VALUE);
        gScore[startNode] = 0;
        fScore[startNode] = heuristic(startNode, goalNode);

        PriorityQueue<int[]> open = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        open.offer(new int[]{startNode, fScore[startNode]});

        boolean[] inOpen = new boolean[nodeCount];
        boolean[] inClosed = new boolean[nodeCount];
        inOpen[startNode] = true;

        int stepIndex = 0;
        steps.add(new StepDTO(
                stepIndex++,
                "Initial A* scores",
                null,
                buildMeta(gScore, fScore, inOpen, inClosed, startNode, goalNode, "init")
        ));

        while (!open.isEmpty()) {
            int[] cur = open.poll();
            int u = cur[0];
            if (inClosed[u]) {
                continue;
            }
            inClosed[u] = true;
            inOpen[u] = false;

            steps.add(new StepDTO(
                    stepIndex++,
                    "Expand node " + u,
                    null,
                    buildMeta(gScore, fScore, inOpen, inClosed, u, goalNode, "expand")
            ));

            if (u == goalNode) {
                steps.add(new StepDTO(
                        stepIndex,
                        "Reached goal node " + goalNode,
                        null,
                        buildMeta(gScore, fScore, inOpen, inClosed, u, goalNode, "goal")
                ));
                return steps;
            }

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (inClosed[v]) {
                    continue;
                }

                int tentativeG = gScore[u] == Integer.MAX_VALUE ? Integer.MAX_VALUE : gScore[u] + w;
                if (tentativeG < gScore[v]) {
                    gScore[v] = tentativeG;
                    fScore[v] = tentativeG == Integer.MAX_VALUE ? Integer.MAX_VALUE : tentativeG + heuristic(v, goalNode);
                    open.offer(new int[]{v, fScore[v]});
                    inOpen[v] = true;

                    steps.add(new StepDTO(
                            stepIndex++,
                            "Update neighbor " + v + " from node " + u,
                            null,
                            buildMeta(gScore, fScore, inOpen, inClosed, v, goalNode, "update")
                    ));
                }
            }
        }

        steps.add(new StepDTO(
                stepIndex,
                "Open set exhausted – goal not reachable",
                null,
                buildMeta(gScore, fScore, inOpen, inClosed, -1, goalNode, "done")
        ));

        return steps;
    }

    private List<List<int[]>> buildGraph(int n, List<int[]> edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        if (edges != null) {
            for (int[] e : edges) {
                if (e.length >= 3) {
                    int from = e[0];
                    int to = e[1];
                    int weight = e[2];
                    if (from >= 0 && from < n && to >= 0 && to < n) {
                        graph.get(from).add(new int[]{to, weight});
                    }
                }
            }
        }
        return graph;
    }

    // Simple heuristic: zero (can be customized later)
    private int heuristic(int node, int goal) {
        return 0;
    }

    private Map<String, Object> buildMeta(int[] gScore, int[] fScore,
                                          boolean[] inOpen, boolean[] inClosed,
                                          int current, int goal, String type) {
        Map<String, Object> meta = new HashMap<>();

        List<Integer> gList = new ArrayList<>(gScore.length);
        for (int g : gScore) {
            gList.add(g == Integer.MAX_VALUE ? -1 : g);
        }
        List<Integer> fList = new ArrayList<>(fScore.length);
        for (int f : fScore) {
            fList.add(f == Integer.MAX_VALUE ? -1 : f);
        }
        List<Boolean> openList = new ArrayList<>(inOpen.length);
        for (boolean b : inOpen) {
            openList.add(b);
        }
        List<Boolean> closedList = new ArrayList<>(inClosed.length);
        for (boolean b : inClosed) {
            closedList.add(b);
        }

        meta.put("gScore", gList);
        meta.put("fScore", fList);
        meta.put("inOpen", openList);
        meta.put("inClosed", closedList);
        meta.put("current", current);
        meta.put("goal", goal);
        meta.put("type", type);
        return meta;
    }
}


