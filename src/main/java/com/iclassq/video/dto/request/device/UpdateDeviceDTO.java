package com.iclassq.video.dto.request.device;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceDTO {
    @Size(max = 100)
    private String deviceName;

    private Integer deviceTypeId;

    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    private String deviceUsername;

    private Boolean isActive;
}
