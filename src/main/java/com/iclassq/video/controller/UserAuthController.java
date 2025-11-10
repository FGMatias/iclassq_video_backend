package com.iclassq.video.controller;

import com.iclassq.video.dto.request.auth.LoginDTO;
import com.iclassq.video.dto.response.user.UserAuthResponseDTO;
import com.iclassq.video.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponseDTO> login(@RequestBody @Valid LoginDTO dto) {
        UserAuthResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}
