package com.iclass.video.dto.response.config;

import com.iclass.video.enums.ConfigCategory;
import com.iclass.video.enums.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchConfigResponseDTO {
    private Integer id;
    private Integer branchId;
    private String branchName;
    private String companyName;
    private ConfigCategory category;
    private String configKey;
    private String configValue;
    private ConfigType configType;
    private String defaultValue;
    private String description;
    private String validationRule;
    private Integer displayOrder;
    private String updatedByUsername;
    private LocalDateTime updatedAt;
}
