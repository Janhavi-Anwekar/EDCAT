package com.ecdat.backend.risk;

public record Classification(QuantumStatus status, String reason) {
    public boolean isVulnerable() {
        return status != QuantumStatus.SAFE;
    }
}

