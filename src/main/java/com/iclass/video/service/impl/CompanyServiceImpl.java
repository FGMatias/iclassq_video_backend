package com.iclass.video.service.impl;

import com.iclass.video.dto.request.company.CreateCompanyDTO;
import com.iclass.video.dto.request.company.UpdateCompanyDTO;
import com.iclass.video.dto.response.company.CompanyResponseDTO;
import com.iclass.video.entity.Company;
import com.iclass.video.exception.DuplicateEntityException;
import com.iclass.video.exception.ResourceNotFoundException;
import com.iclass.video.mapper.CompanyMapper;
import com.iclass.video.repository.CompanyRepository;
import com.iclass.video.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toResponseDTOList(companies);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDTO findById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", id));

        return companyMapper.toResponseDTO(company);
    }

    @Override
    @Transactional
    public CompanyResponseDTO create(CreateCompanyDTO dto) {
        if (companyRepository.existsByName(dto.getName())) {
            throw new DuplicateEntityException("Empresa", "nombre", dto.getName());
        }

        if (companyRepository.existsByRuc(dto.getRuc())) {
            throw new DuplicateEntityException("Empresa", "ruc", dto.getRuc());
        }

        Company company = companyMapper.toEntity(dto);
        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponseDTO(savedCompany);
    }

    @Override
    @Transactional
    public CompanyResponseDTO update(Integer id, UpdateCompanyDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", id));

        if (dto.getName() != null && !dto.getName().equals(company.getName())) {
            if (companyRepository.existsByName(dto.getName())) {
                throw new DuplicateEntityException("Empresa", "nombre", dto.getName());
            }
        }

        if (dto.getRuc() != null && !dto.getRuc().equals(company.getRuc())) {
            if (companyRepository.existsByRuc(dto.getRuc())) {
                throw new DuplicateEntityException("Empresa", "ruc", dto.getRuc());
            }
        }

        companyMapper.updateEntity(company, dto);

        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toResponseDTO(updatedCompany);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empresa", id);
        }

        companyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activate(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", id));

        company.setIsActive(true);
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public void deactivate(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", id));

        company.setIsActive(false);
        companyRepository.save(company);
    }
}
