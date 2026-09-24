package com.ecdat.backend.risk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecommendationEngineTest {

    private final RecommendationEngine engine = new RecommendationEngine();

    @Test
    void rsaGetsMlKem() {
        assertEquals("ML-KEM-768", engine.recommend("RSA", false).primary());
    }

    @Test
    void ecdsaGetsMlDsa() {
        assertEquals("ML-DSA-65", engine.recommend("SHA256withECDSA", false).primary());
    }

    @Test
    void aes128GetsAes256() {
        assertEquals("AES-256", engine.recommend("AES-128", false).primary());
    }

    @Test
    void safeAlgorithmGetsNoRecommendation() {
        assertNull(engine.recommend("AES-256", false));
    }
}
