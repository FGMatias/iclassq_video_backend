package com.iclassq.video.service;

import com.iclassq.video.dto.request.auth.LoginDTO;
import com.iclassq.video.dto.request.user.CreateBranchAdminDTO;
import com.iclassq.video.dto.request.user.CreateCompanyAdminDTO;
import com.iclassq.video.dto.request.user.CreateUserDTO;
import com.iclassq.video.dto.request.user.UpdateUserDTO;
import com.iclassq.video.dto.response.user.UserAuthResponseDTO;
import com.iclassq.video.dto.response.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(Integer id);
    UserResponseDTO create(CreateUserDTO dto);
    UserResponseDTO createCompanyAdmin(CreateCompanyAdminDTO dto);
    UserResponseDTO createBranchAdmin(CreateBranchAdminDTO dto);
    UserResponseDTO update(Integer id, UpdateUserDTO dto);
    void delete(Integer id);
    void activate(Integer id);
    void deactivate(Integer id);
    void resetPassword(Integer id, String newPassword);

    UserAuthResponseDTO login(LoginDTO dto);
}
