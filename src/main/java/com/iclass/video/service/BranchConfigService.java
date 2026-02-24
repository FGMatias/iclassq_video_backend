package com.iclass.video.service;

import com.iclass.video.dto.request.config.UpdateBranchConfigDTO;
import com.iclass.video.dto.response.config.BranchConfigGroupedDTO;
import com.iclass.video.dto.response.config.BranchConfigResponseDTO;
import com.iclass.video.enums.ConfigCategory;

import java.util.List;

public interface BranchConfigService {
    List<BranchConfigResponseDTO> findByBranch(Integer branchId);
    BranchConfigGroupedDTO findByBranchGrouped(Integer branchId);
    List<BranchConfigResponseDTO> findByBranchAndCategory(Integer branchId, ConfigCategory category);
    BranchConfigResponseDTO findById(Integer id);
    BranchConfigResponseDTO update(Integer id, UpdateBranchConfigDTO dto, Integer userId);
    void resetToDefault(Integer id);
    String getConfigValue(Integer branchId, String key);
    Integer getConfigValueAsInt(Integer branchId, String key);
    Boolean getConfigValueAsBoolean(Integer branchId, String key);
}
