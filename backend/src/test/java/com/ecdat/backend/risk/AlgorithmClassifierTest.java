package com.ecdat.backend.risk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmClassifierTest {

    @Test
    void rsaIsVulnerable() {
        assertEquals(QuantumStatus.VULNERABLE, AlgorithmClassifier.classify("RSA").status());
    }

    @Test
    void ecdsaIsVulnerable() {
        assertEquals(QuantumStatus.VULNERABLE, AlgorithmClassifier.classify("SHA256withECDSA").status());
    }

    @Test
    void aes128IsWeakened() {
        assertEquals(QuantumStatus.WEAKENED, AlgorithmClassifier.classify("AES-128").status());
    }

    @Test
    void aes256IsSafe() {
        assertEquals(QuantumStatus.SAFE, AlgorithmClassifier.classify("AES-256").status());
    }

    @Test
    void md5IsBroken() {
        assertEquals(QuantumStatus.BROKEN, AlgorithmClassifier.classify("MD5").status());
    }

    @Test
    void mlKemIsSafe() {
        assertEquals(QuantumStatus.SAFE, AlgorithmClassifier.classify("ML-KEM-768").status());
    }

}
