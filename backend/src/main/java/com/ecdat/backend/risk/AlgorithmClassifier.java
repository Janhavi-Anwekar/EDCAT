package com.ecdat.backend.risk;

public class AlgorithmClassifier {

    public static Classification classify(String rawName) {
        String n = rawName.toUpperCase().replace("_", "-");

        // Already broken: check these first
        if (n.contains("MD5") || n.contains("SHA-1") || n.equals("SHA1") || n.startsWith("DES"))
            return new Classification(QuantumStatus.BROKEN, "Already insecure against classical attacks");

        // Post-quantum: safe
        if (n.contains("ML-KEM") || n.contains("ML-DSA") || n.contains("SLH-DSA") || n.contains("KYBER")
                || n.contains("DILITHIUM"))
            return new Classification(QuantumStatus.SAFE, "NIST post-quantum algorithm");

        // Broken by Shor's algorithm
        if (n.contains("RSA") || n.contains("ECDSA") || n.contains("ECDH") || n.contains("EDDSA") || n.contains("ED25519")
                || n.equals("EC") || n.startsWith("DH") || n.equals("DSA") || n.contains("DIFFIE"))
            return new Classification(QuantumStatus.VULNERABLE, "Broken by Shor's algorithm");

        // Symmetric: AES depends on key size
        if (n.contains("AES")) {
            if (n.contains("128"))
                return new Classification(QuantumStatus.WEAKENED, "Grover halves strength; upgrade to AES-256");
            return new Classification(QuantumStatus.SAFE, "AES-192/256 is adequate against Grover");
        }

        // Hashes
        if (n.contains("SHA-256") || n.contains("SHA-384") || n.contains("SHA-512") || n.contains("SHA3"))
            return new Classification(QuantumStatus.SAFE, "Strong hash");

        return new Classification(QuantumStatus.SAFE, "Not recognized; treated as safe (review manually)");
    }

}
