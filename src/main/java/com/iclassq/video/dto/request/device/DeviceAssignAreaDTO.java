package com.iclassq.video.dto.request.device;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAssignAreaDTO {
    @NotNull(message = "El área es obligatoria")
    private Integer areaId;

    private String notes;
}
