package com.ecdat.backend.scan;

import com.ecdat.backend.risk.AssetRisk;
import java.util.List;

public record ScanResult(Long id, String profileName, List<AssetRisk> assets) {
}
