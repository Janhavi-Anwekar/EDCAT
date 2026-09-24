package com.ecdat.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public String health() {return "ok";}
}
