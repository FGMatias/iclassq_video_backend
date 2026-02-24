package com.iclass.video.dto.response.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchConfigGroupedDTO {
    private Integer branchId;
    private String branchName;
    private String companyName;
    private Map<String, List<BranchConfigResponseDTO>> configsByCategory;
}
