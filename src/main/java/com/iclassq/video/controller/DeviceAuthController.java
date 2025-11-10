package com.iclassq.video.controller;

import com.iclassq.video.dto.request.auth.LoginDTO;
import com.iclassq.video.dto.response.device.DeviceAuthResponseDTO;
import com.iclassq.video.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device/auth")
@RequiredArgsConstructor
public class DeviceAuthController {

    private final DeviceService deviceService;

    @PostMapping("/login")
    public ResponseEntity<DeviceAuthResponseDTO> login(@RequestBody @Valid LoginDTO dto) {
        DeviceAuthResponseDTO response = deviceService.login(
                dto.getUsername(),
                dto.getPassword()
        );

        return ResponseEntity.ok(response);
    }
}
