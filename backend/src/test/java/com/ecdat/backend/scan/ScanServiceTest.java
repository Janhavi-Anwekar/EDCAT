package com.ecdat.backend.scan;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ScanServiceTest {
    @Test
    void runsFullScan() throws Exception {
        ScanService service = new ScanService();
        try (FileInputStream in = new FileInputStream("../samples/vulnerable-java-app.cbom.json")) {
            ScanResult result = service.runScan(in, "DEFENSE", 15);
            assertEquals(5, result.assets().size());
            assertEquals("CRITICAL", result.assets().get(0).risk()); // RSA under Defense
        }
    }
}
