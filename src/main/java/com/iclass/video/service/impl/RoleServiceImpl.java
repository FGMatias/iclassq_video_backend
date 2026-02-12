package com.iclass.video.service.impl;

import com.iclass.video.entity.Role;
import com.iclass.video.repository.RoleRepository;
import com.iclass.video.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
