package com.iclassq.video.service.impl;

import com.iclassq.video.dto.request.device.DeviceAssignAreaDTO;
import com.iclassq.video.dto.request.device.RegisterDeviceDTO;
import com.iclassq.video.dto.response.device.DeviceResponseDTO;
import com.iclassq.video.entity.DeviceArea;
import com.iclassq.video.repository.AreaRepository;
import com.iclassq.video.repository.DeviceAreaRepository;
import com.iclassq.video.repository.DeviceRepository;
import com.iclassq.video.repository.UserRepository;
import com.iclassq.video.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceAreaRepository deviceAreaRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DeviceResponseDTO register(RegisterDeviceDTO dto, Integer adminUserId) {
        return null;
    }

    @Override
    public void reassign(Integer deviceId, DeviceAssignAreaDTO dto, Integer adminUserId) {

    }

    @Override
    public DeviceResponseDTO getDeviceWithCurrentArea(Integer deviceId) {
        return null;
    }

    @Override
    public List<DeviceArea> getHistory(Integer deviceId) {
        return List.of();
    }

    @Override
    public void deactivate(Integer deviceId) {

    }
}
