package com.example.demo.service;

import com.example.demo.dto.StepDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService {

    public List<StepDTO> visualize(int nodeCount, List<int[]> edges, int startNode) {
        List<StepDTO> steps = new ArrayList<>();
        if (nodeCount <= 0 || startNode < 0 || startNode >= nodeCount) {
            return steps;
        }

        List<List<int[]>> graph = buildGraph(nodeCount, edges);
        int[] dist = new int[nodeCount];
        boolean[] visited = new boolean[nodeCount];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{startNode, 0});

        int stepIndex = 0;
        steps.add(new StepDTO(
                stepIndex++,
                "Initial distances",
                null,
                buildMeta(dist, visited, startNode, "init")
        ));

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            steps.add(new StepDTO(
                    stepIndex++,
                    "Visit node " + u,
                    null,
                    buildMeta(dist, visited, u, "visit")
            ));

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});

                    steps.add(new StepDTO(
                            stepIndex++,
                            "Relax edge " + u + " -> " + v + " with weight " + w,
                            null,
                            buildMeta(dist, visited, v, "relax")
                    ));
                }
            }
        }

        steps.add(new StepDTO(
                stepIndex,
                "Final shortest distances",
                null,
                buildMeta(dist, visited, -1, "done")
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

    private Map<String, Object> buildMeta(int[] dist, boolean[] visited, int current, String type) {
        Map<String, Object> meta = new HashMap<>();
        List<Integer> distList = new ArrayList<>(dist.length);
        for (int d : dist) {
            distList.add(d == Integer.MAX_VALUE ? -1 : d);
        }
        List<Boolean> visitedList = new ArrayList<>(visited.length);
        for (boolean v : visited) {
            visitedList.add(v);
        }
        meta.put("dist", distList);
        meta.put("visited", visitedList);
        meta.put("current", current);
        meta.put("type", type);
        return meta;
    }
}


