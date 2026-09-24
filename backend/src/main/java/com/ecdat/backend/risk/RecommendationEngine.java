package com.ecdat.backend.risk;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class RecommendationEngine {

    private final Map<String, Recommendation> table;

    public RecommendationEngine() {
        try (InputStream in = getClass().getResourceAsStream("/recommendations.json")) {
            table = new ObjectMapper().readValue(in, new TypeReference<>() {});
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load recommendations.json", e);
        }
    }

    /** Returns null if the algorithm is safe (nothing to recommend). */
    public Recommendation recommend(String rawName, boolean isSignature) {
        Classification c = AlgorithmClassifier.classify(rawName);
        String n = rawName.toUpperCase();

        switch (c.status()) {
            case SAFE:
                return null;
            case WEAKENED:
                return table.get("AES_128");
            case BROKEN:
                boolean hash = n.contains("MD5") || n.contains("SHA");
                return table.get(hash ? "BROKEN_HASH" : "BROKEN_CIPHER");
            case VULNERABLE:
                boolean sig = isSignature || n.contains("DSA") || n.contains("ED25519");
                return table.get(sig ? "SIGNATURE" : "KEY_EXCHANGE");
            default:
                return null;
        }
    }
}
