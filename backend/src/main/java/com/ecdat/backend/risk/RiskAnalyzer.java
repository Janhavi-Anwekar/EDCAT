package com.ecdat.backend.risk;

public class RiskAnalyzer {

    private final RecommendationEngine recommender = new RecommendationEngine();

    public AssetRisk analyze(String name, boolean isSignature,
                             double x, double y, double z) {
        Classification c = AlgorithmClassifier.classify(name);
        RiskResult r = MoscaCalculator.assess(x, y, z, c.isVulnerable());
        Recommendation rec = recommender.recommend(name, isSignature);

        return new AssetRisk(name, c.status().name(), c.isVulnerable(), c.reason(),
                x, y, z, r.margin(), r.level(), rec);
    }
}
