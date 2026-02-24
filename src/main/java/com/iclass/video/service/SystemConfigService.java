package com.iclass.video.service;

import com.iclass.video.dto.request.config.UpdateSystemConfigDTO;
import com.iclass.video.dto.response.config.SystemConfigResponseDTO;

import java.util.List;

public interface SystemConfigService {
    List<SystemConfigResponseDTO> findAll();
    SystemConfigResponseDTO findById(Integer id);
    SystemConfigResponseDTO findByKey(String key);
    SystemConfigResponseDTO update(Integer id, UpdateSystemConfigDTO dto, Integer userId);
    void resetToDefault(Integer id);
    String getConfigValue(String key);
    Integer getConfigValueAsInt(String key);
    Boolean getConfigValueAsBoolean(String key);
}
