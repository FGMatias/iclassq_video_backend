package com.iclass.video.controller;

import com.iclass.video.dto.request.config.UpdateBranchConfigDTO;
import com.iclass.video.dto.response.config.BranchConfigGroupedDTO;
import com.iclass.video.dto.response.config.BranchConfigResponseDTO;
import com.iclass.video.enums.ConfigCategory;
import com.iclass.video.security.SecurityUtils;
import com.iclass.video.service.BranchConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch-config")
@RequiredArgsConstructor
public class BranchConfigController {

    private final BranchConfigService branchConfigService;
    private final SecurityUtils securityUtils;

    @GetMapping("/branch/{branchId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<List<BranchConfigResponseDTO>> findByBranch(@PathVariable Integer branchId) {
        List<BranchConfigResponseDTO> response = branchConfigService.findByBranch(branchId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/branch/{branchId}/grouped")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<BranchConfigGroupedDTO> findByBranchGrouped(@PathVariable Integer branchId) {
        BranchConfigGroupedDTO response = branchConfigService.findByBranchGrouped(branchId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/branch/{branchId}/category/{category}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<List<BranchConfigResponseDTO>> findByBranchAndCategory(
            @PathVariable Integer branchId,
            @PathVariable ConfigCategory category
    ) {
        List<BranchConfigResponseDTO> response = branchConfigService
                .findByBranchAndCategory(branchId, category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<BranchConfigResponseDTO> findById(@PathVariable Integer id) {
        BranchConfigResponseDTO response = branchConfigService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<BranchConfigResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateBranchConfigDTO dto
    ) {
        Integer userId = securityUtils.getCurrentUserId();
        BranchConfigResponseDTO response = branchConfigService.update(id, dto, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reset")
    @PreAuthorize("hasAnyRole('SUPER_ADMINISTRADOR', 'ADMINISTRADOR_EMPRESA', 'ADMINISTRADOR_SUCURSAL')")
    public ResponseEntity<Void> resetToDefault(@PathVariable Integer id) {
        branchConfigService.resetToDefault(id);
        return ResponseEntity.ok().build();
    }
}
