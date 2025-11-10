package com.iclassq.video.controller;

import com.iclassq.video.dto.request.device.CreateDeviceDTO;
import com.iclassq.video.dto.request.device.DeviceAssignAreaDTO;
import com.iclassq.video.dto.request.device.UpdateDeviceDTO;
import com.iclassq.video.dto.response.device.DeviceResponseDTO;
import com.iclassq.video.security.SecurityUtils;
import com.iclassq.video.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final SecurityUtils securityUtils;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<List<DeviceResponseDTO>> findAll() {
        List<DeviceResponseDTO> response = deviceService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<DeviceResponseDTO> findById(@PathVariable Integer id) {
        DeviceResponseDTO response = deviceService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<DeviceResponseDTO> create(@RequestBody @Valid CreateDeviceDTO dto) {
        Integer adminUserId = securityUtils.getCurrentUserId();
        DeviceResponseDTO response = deviceService.create(dto, adminUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<DeviceResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateDeviceDTO dto
    ) {
        DeviceResponseDTO response = deviceService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<DeviceResponseDTO> delete(@PathVariable Integer id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{deviceId}/reassign")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<Void> reassign(
            @PathVariable Integer deviceId,
            @RequestBody @Valid DeviceAssignAreaDTO dto
    ) {
        Integer adminUserId = securityUtils.getCurrentUserId();
        deviceService.reassign(deviceId, dto, adminUserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{deviceId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<DeviceResponseDTO> getWithCurrentArea(@PathVariable Integer deviceId) {
        DeviceResponseDTO response = deviceService.getDeviceWithCurrentArea(deviceId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{deviceId}/deactivate")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<Void> deactivate(@PathVariable Integer deviceId) {
        deviceService.deactivate(deviceId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{deviceId}/sync")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updateSync(@PathVariable Integer deviceId) {
        deviceService.updateLastSync(deviceId);
        return ResponseEntity.ok().build();
    }
}
