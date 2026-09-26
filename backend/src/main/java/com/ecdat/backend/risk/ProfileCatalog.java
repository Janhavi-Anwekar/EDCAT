package com.ecdat.backend.risk;

import java.util.Map;

public class ProfileCatalog {

    private static final Map<String, Profile> PROFILES = Map.of(
            "DEFENSE", new Profile("Defense / State Secrets", 30, 5),
            "MEDICAL", new Profile("Medical / Identity Records", 25, 4),
            "FINANCIAL", new Profile("Financial Records", 10, 3),
            "SESSION", new Profile("Session Tokens / Temp Data", 1, 1)
    );

    public static Profile get(String key) {
        Profile p = PROFILES.get(key.toUpperCase());
        if (p == null) throw new IllegalArgumentException("Unknown profile: " + key);
        return p;
    }
}
