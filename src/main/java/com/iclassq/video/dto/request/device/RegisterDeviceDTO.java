package com.iclassq.video.dto.request.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDeviceDTO {
    @NotBlank(message = "El nombre del dispositivo es obligatorio")
    @Size(max = 100)
    private String deviceName;

    @Size(max = 100)
    private String deviceIdentifier;

    @NotNull(message = "El tipo de dispositivo es obligatorio")
    private Integer deviceTypeId;

    @NotNull(message = "El área es obligatoria")
    private Integer areaId;

    private String notes;
}
