package com.example.demo.service;

import com.example.demo.dto.StepDTO;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MerkleService {

    /**
     * Build a Merkle tree from a list of data blocks and
     * emit steps for each level construction.
     */
    public List<StepDTO> visualize(List<String> dataBlocks) {
        List<StepDTO> steps = new ArrayList<>();
        if (dataBlocks == null || dataBlocks.isEmpty()) {
            return steps;
        }

        int stepIndex = 0;
        List<String> currentLevel = new ArrayList<>();
        for (String block : dataBlocks) {
            currentLevel.add(hash(block));
        }

        Map<String, Object> leafMeta = new HashMap<>();
        leafMeta.put("level", 0);
        leafMeta.put("hashes", new ArrayList<>(currentLevel));
        steps.add(new StepDTO(
                stepIndex++,
                "Leaf hashes",
                null,
                leafMeta
        ));

        int level = 1;
        while (currentLevel.size() > 1) {
            List<String> nextLevel = new ArrayList<>();
            List<List<String>> pairs = new ArrayList<>();

            for (int i = 0; i < currentLevel.size(); i += 2) {
                String left = currentLevel.get(i);
                String right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : left;
                String combined = hash(left + right);
                nextLevel.add(combined);
                pairs.add(Arrays.asList(left, right, combined));
            }

            Map<String, Object> meta = new HashMap<>();
            meta.put("level", level);
            meta.put("nodes", new ArrayList<>(nextLevel));
            meta.put("pairs", pairs);

            steps.add(new StepDTO(
                    stepIndex++,
                    "Build level " + level,
                    null,
                    meta
            ));

            currentLevel = nextLevel;
            level++;
        }

        Map<String, Object> rootMeta = new HashMap<>();
        rootMeta.put("level", level - 1);
        rootMeta.put("root", currentLevel.get(0));
        steps.add(new StepDTO(
                stepIndex,
                "Merkle root",
                null,
                rootMeta
        ));

        return steps;
    }

    private String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}


