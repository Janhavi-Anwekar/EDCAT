package com.ecdat.backend.cbom;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CbomParserTest {

    @Test
    void parsesSampleCbom() throws Exception {
        CbomParser parser = new CbomParser();
        try (FileInputStream in = new FileInputStream("../samples/vulnerable-java-app.cbom.json")) {
            List<CbomComponent> components = parser.parse(in);
            assertEquals(5, components.size());
            assertEquals("RSA", components.get(0).name());
            assertEquals("SHA256withECDSA", components.get(1).name());
            assertEquals("AES-128", components.get(2).name());
            assertEquals("MD5", components.get(3).name());
            assertEquals("SHA-384", components.get(4).name());

        }
    }
}