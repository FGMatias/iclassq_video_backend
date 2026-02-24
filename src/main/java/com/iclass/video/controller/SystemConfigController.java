package com.iclass.video.controller;

import com.iclass.video.dto.request.config.UpdateSystemConfigDTO;
import com.iclass.video.dto.response.config.SystemConfigResponseDTO;
import com.iclass.video.security.SecurityUtils;
import com.iclass.video.service.SystemConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system-config")
@RequiredArgsConstructor
public class SystemConfigController {

    private final SystemConfigService systemConfigService;
    private final SecurityUtils securityUtils;

    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMINISTRADOR')")
    public ResponseEntity<List<SystemConfigResponseDTO>> findAll() {
        List<SystemConfigResponseDTO> response = systemConfigService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMINISTRADOR')")
    public ResponseEntity<SystemConfigResponseDTO> findById(@PathVariable Integer id) {
        SystemConfigResponseDTO response = systemConfigService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{key}")
    @PreAuthorize("hasRole('SUPER_ADMINISTRADOR')")
    public ResponseEntity<SystemConfigResponseDTO> findByKey(@PathVariable String key) {
        SystemConfigResponseDTO response = systemConfigService.findByKey(key);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMINISTRADOR')")
    public ResponseEntity<SystemConfigResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateSystemConfigDTO dto
    ) {
        Integer userId = securityUtils.getCurrentUserId();
        SystemConfigResponseDTO response = systemConfigService.update(id, dto, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reset")
    @PreAuthorize("hasRole('SUPER_ADMINISTRADOR')")
    public ResponseEntity<Void> resetToDefault(@PathVariable Integer id) {
        systemConfigService.resetToDefault(id);
        return ResponseEntity.ok().build();
    }
}
