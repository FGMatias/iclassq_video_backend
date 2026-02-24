package com.iclass.video.service.impl;

import com.iclass.video.dto.request.config.UpdateSystemConfigDTO;
import com.iclass.video.dto.response.config.SystemConfigResponseDTO;
import com.iclass.video.entity.SystemConfig;
import com.iclass.video.entity.User;
import com.iclass.video.enums.ConfigType;
import com.iclass.video.exception.BadRequestException;
import com.iclass.video.exception.ResourceNotFoundException;
import com.iclass.video.mapper.SystemConfigMapper;
import com.iclass.video.repository.SystemConfigRepository;
import com.iclass.video.repository.UserRepository;
import com.iclass.video.service.SystemConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SystemConfigServiceImpl implements SystemConfigService {

    private final SystemConfigRepository systemConfigRepository;
    private final UserRepository userRepository;
    private final SystemConfigMapper systemConfigMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SystemConfigResponseDTO> findAll() {
        List<SystemConfig> configs = systemConfigRepository.findAllOrderedByDisplayOrder();
        return systemConfigMapper.toResponseDTOList(configs);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemConfigResponseDTO findById(Integer id) {
        SystemConfig config = systemConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración del sistema", id));
        return systemConfigMapper.toResponseDTO(config);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemConfigResponseDTO findByKey(String key) {
        SystemConfig config = systemConfigRepository.findByConfigKey(key)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración", "key", key));
        return systemConfigMapper.toResponseDTO(config);
    }

    @Override
    @Transactional
    public SystemConfigResponseDTO update(Integer id, UpdateSystemConfigDTO dto, Integer userId) {
        SystemConfig config = systemConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración del sistema", id));

        validateConfigValue(dto.getConfigValue(), config.getConfigType(), config.getValidationRule());

        User user = null;
        if (userId != null) {
            user = userRepository.findById(userId).orElse(null);
        }

        systemConfigMapper.updateEntity(config, dto, user);
        SystemConfig updatedConfig = systemConfigRepository.save(config);

        return systemConfigMapper.toResponseDTO(updatedConfig);
    }

    @Override
    @Transactional
    public void resetToDefault(Integer id) {
        SystemConfig config = systemConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración del sistema", id));

        config.setConfigValue(config.getDefaultValue());
        systemConfigRepository.save(config);
    }

    @Override
    @Transactional(readOnly = true)
    public String getConfigValue(String key) {
        return systemConfigRepository.findByConfigKey(key)
                .map(SystemConfig::getConfigValue)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración", "key", key));
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getConfigValueAsInt(String key) {
        String value = getConfigValue(key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El valor de '" + key + "' no es un número válido");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getConfigValueAsBoolean(String key) {
        String value = getConfigValue(key);
        return Boolean.parseBoolean(value);
    }

    private void validateConfigValue(String value, ConfigType type, String validationRule) {
        switch (type) {
            case NUMBER:
                validateNumber(value, validationRule);
                break;
            case BOOLEAN:
                validateBoolean(value);
                break;
            case PATH:
                validatePath(value, validationRule);
                break;
            case STRING:
                validateString(value, validationRule);
                break;
            case JSON:
                break;
        }
    }

    private void validateNumber(String value, String validationRule) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El valor debe ser un número válido");
        }

        if (validationRule != null && !validationRule.isEmpty()) {
            if (!Pattern.matches(validationRule, value)) {
                throw new BadRequestException("El valor no cumple con el formato numérico requerido");
            }
        }
    }

    private void validateBoolean(String value) {
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new BadRequestException("El valor debe ser 'true' o 'false'");
        }
    }

    private void validatePath(String value, String validationRule) {
        if (validationRule != null && !validationRule.isEmpty()) {
            if (!Pattern.matches(validationRule, value)) {
                throw new BadRequestException("La ruta no tiene un formato válido (ej: C:/carpeta/subcarpeta)");
            }
        }
    }

    private void validateString(String value, String validationRule) {
        if (validationRule != null && !validationRule.isEmpty()) {
            if (!Pattern.matches(validationRule, value)) {
                throw new BadRequestException("El valor no cumple con el formato requerido");
            }
        }
    }
}
