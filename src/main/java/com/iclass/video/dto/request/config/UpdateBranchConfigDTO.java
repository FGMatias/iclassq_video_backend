package com.iclass.video.dto.request.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchConfigDTO {
    @NotBlank(message = "El valor es obligatorio")
    private String configValue;

    @Size(max = 255)
    private String description;
    private Integer displayOrder;
}
