package com.ecdat.backend.api;

import com.ecdat.backend.scan.ScanResult;
import com.ecdat.backend.scan.ScanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/scans")
@CrossOrigin(origins = "http://localhost:5173")
public class ScanController {

    private final ScanService scanService = new ScanService();

    @PostMapping
    public ScanResult createScan(
            @RequestParam("file") MultipartFile file,
            @RequestParam("profile") String profile,
            @RequestParam(value = "z", defaultValue = "15") double z) throws Exception {
        return scanService.runScan(file.getInputStream(), profile, z);
    }

    @GetMapping("/{id}")
    public ScanResult getScan(@PathVariable Long id) {
        ScanResult result = scanService.getScan(id);
        if (result == null) {
            throw new RuntimeException("Scan not found: " + id);
        }
        return result;
    }
}
