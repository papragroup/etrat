package com.etrat.web.rest;

import com.etrat.service.dto.CheckVersionRequest;
import com.etrat.service.dto.VersionSupportDotResponse;
import com.etrat.service.impl.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VersionResource {
    @Autowired
    private AppVersionService appVersionService;

    @GetMapping("/check-version")
    public ResponseEntity<VersionSupportDotResponse> checkVersion(CheckVersionRequest checkVersionRequest) {
        return ResponseEntity.ok(appVersionService.checkVersion(checkVersionRequest));
    }
}
