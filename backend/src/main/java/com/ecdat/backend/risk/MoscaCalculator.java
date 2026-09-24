package com.ecdat.backend.risk;

public class MoscaCalculator {

    public static  RiskResult assess(double x, double y, double z, boolean vulnerable) {
        if(!vulnerable) {
            return new RiskResult("SAFE", 0);
        }
        double margin = (x + y) - z;

        String level;
        if (margin > 0 && x >= z) level = "CRITICAL";
        else if (margin > 0)      level = "HIGH";
        else if (margin > -5)     level = "MEDIUM";
        else                      level = "LOW";

        return new RiskResult(level, margin);
    }
}
