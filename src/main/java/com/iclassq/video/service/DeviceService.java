package com.iclassq.video.service;

import com.iclassq.video.dto.request.device.DeviceAssignAreaDTO;
import com.iclassq.video.dto.request.device.CreateDeviceDTO;
import com.iclassq.video.dto.request.device.UpdateDeviceDTO;
import com.iclassq.video.dto.response.device.DeviceAuthResponseDTO;
import com.iclassq.video.dto.response.device.DeviceResponseDTO;
import com.iclassq.video.entity.DeviceArea;

import java.util.List;

public interface DeviceService {
    List<DeviceResponseDTO> findAll();
    DeviceResponseDTO findById(Integer id);
    DeviceResponseDTO create(CreateDeviceDTO dto, Integer adminUserId);
    DeviceResponseDTO update(Integer id, UpdateDeviceDTO dto);
    void delete(Integer id);
    void reassign(Integer id, DeviceAssignAreaDTO dto, Integer adminUserId);
    DeviceResponseDTO getDeviceWithCurrentArea(Integer id);
    List<DeviceArea> getHistory(Integer id);
    void activate(Integer id);
    void deactivate(Integer id);
    void updateLastSync(Integer id);

    DeviceAuthResponseDTO login(String deviceUsername, String devicePassword);
}
