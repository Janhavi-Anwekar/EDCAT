package com.ecdat.backend.risk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoscaCalculatorTest {

    @Test
    void defenseProfileIsCritical() {
        assertEquals("CRITICAL", MoscaCalculator.assess(30, 5, 15, true).level());
    }

    @Test
    void sessionTokensAreLow() {
        assertEquals("LOW", MoscaCalculator.assess(1, 1, 15, true).level());
    }

    @Test
    void financialIsMedium() {
        assertEquals("MEDIUM", MoscaCalculator.assess(10, 3, 15, true).level());
    }

    @Test
    void exposedButDataDoesNotOutliveQuantumIsHigh() {
        assertEquals("HIGH", MoscaCalculator.assess(8, 5, 10, true).level());
    }

    @Test
    void nonVulnerableIsSafe() {
        assertEquals("SAFE", MoscaCalculator.assess(30, 5, 15, false).level());
    }


}
