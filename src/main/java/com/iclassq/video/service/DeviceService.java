package com.iclassq.video.service;

import com.iclassq.video.dto.request.device.DeviceAssignAreaDTO;
import com.iclassq.video.dto.request.device.RegisterDeviceDTO;
import com.iclassq.video.dto.response.device.DeviceResponseDTO;
import com.iclassq.video.entity.DeviceArea;

import java.util.List;

public interface DeviceService {
    DeviceResponseDTO register(RegisterDeviceDTO dto, Integer adminUserId);
    void reassign(Integer deviceId, DeviceAssignAreaDTO dto, Integer adminUserId);
    DeviceResponseDTO getDeviceWithCurrentArea(Integer deviceId);
    List<DeviceArea> getHistory(Integer deviceId);
    void deactivate(Integer deviceId);
}
