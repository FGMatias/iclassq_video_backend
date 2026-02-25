package com.iclass.video.service.impl;

import com.iclass.video.dto.request.config.UpdateBranchConfigDTO;
import com.iclass.video.dto.response.config.BranchConfigGroupedDTO;
import com.iclass.video.dto.response.config.BranchConfigResponseDTO;
import com.iclass.video.entity.Branch;
import com.iclass.video.entity.BranchConfig;
import com.iclass.video.entity.User;
import com.iclass.video.enums.ConfigCategory;
import com.iclass.video.enums.ConfigType;
import com.iclass.video.event.BranchConfigChangedEvent;
import com.iclass.video.exception.BadRequestException;
import com.iclass.video.exception.ResourceNotFoundException;
import com.iclass.video.mapper.BranchConfigMapper;
import com.iclass.video.repository.BranchConfigRepository;
import com.iclass.video.repository.BranchRepository;
import com.iclass.video.repository.UserRepository;
import com.iclass.video.service.BranchConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchConfigServiceImpl implements BranchConfigService {

    private final BranchConfigRepository branchConfigRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final BranchConfigMapper branchConfigMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public List<BranchConfigResponseDTO> findByBranch(Integer branchId) {
        if (!branchRepository.existsById(branchId)) {
            throw new ResourceNotFoundException("Sucursal", branchId);
        }

        List<BranchConfig> configs = branchConfigRepository.findByBranch_IdOrderByCategoryAscDisplayOrderAsc(branchId);

        return branchConfigMapper.toResponseDTOList(configs);
    }

    @Override
    @Transactional(readOnly = true)
    public BranchConfigGroupedDTO findByBranchGrouped(Integer branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal", branchId));

        List<BranchConfig> configs = branchConfigRepository.findByBranch_IdOrderByCategoryAscDisplayOrderAsc(branchId);

        Map<String, List<BranchConfigResponseDTO>> grouped = configs.stream()
                .map(branchConfigMapper::toResponseDTO)
                .collect(Collectors.groupingBy(dto -> dto.getCategory().name()));

        return BranchConfigGroupedDTO.builder()
                .branchId(branch.getId())
                .branchName(branch.getName())
                .companyName(branch.getCompany().getName())
                .configsByCategory(grouped)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BranchConfigResponseDTO> findByBranchAndCategory(Integer branchId, ConfigCategory category) {
        List<BranchConfig> configs = branchConfigRepository.findByBranch_IdAndCategory(branchId, category);
        return branchConfigMapper.toResponseDTOList(configs);
    }

    @Override
    @Transactional(readOnly = true)
    public BranchConfigResponseDTO findById(Integer id) {
        BranchConfig config = branchConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración de sucursal", id));

        return branchConfigMapper.toResponseDTO(config);
    }

    @Override
    @Transactional
    public BranchConfigResponseDTO update(Integer id, UpdateBranchConfigDTO dto, Integer userId) {
        BranchConfig config = branchConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración de sucursal", id));

        validateConfigValue(dto.getConfigValue(), config.getConfigType(), config.getValidationRule());

        User user = null;
        if (userId != null) {
            user = userRepository.findById(userId).orElse(null);
        }

        Integer branchId = config.getBranch().getId();
        String oldValue = config.getConfigValue();

        branchConfigMapper.updateEntity(config, dto, user);
        BranchConfig updatedConfig = branchConfigRepository.save(config);

        eventPublisher.publishEvent(new BranchConfigChangedEvent(
                branchId,
                config.getConfigKey(),
                oldValue,
                dto.getConfigValue(),
                userId
        ));

        return branchConfigMapper.toResponseDTO(updatedConfig);
    }

    @Override
    @Transactional
    public void resetToDefault(Integer id) {
        BranchConfig config = branchConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración de sucursal", id));

        config.setConfigValue(config.getDefaultValue());
        branchConfigRepository.save(config);
    }

    @Override
    @Transactional
    public String getConfigValue(Integer branchId, String key) {
        return branchConfigRepository.findByBranchIdAndConfigKey(branchId, key)
                .map(BranchConfig::getConfigValue)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración", branchId));
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getConfigValueAsInt(Integer branchId, String key) {
        String value = getConfigValue(branchId, key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El valor de '" + key + "' no es un número válido");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getConfigValueAsBoolean(Integer branchId, String key) {
        String value = getConfigValue(branchId, key);
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
            case STRING:
                validateString(value, validationRule);
                break;
            case PATH:
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

    private void validateString(String value, String validationRule) {
        if (validationRule != null && !validationRule.isEmpty()) {
            if (!Pattern.matches(validationRule, value)) {
                throw new BadRequestException("El valor no cumple con el formato requerido");
            }
        }
    }
}
