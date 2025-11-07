package com.iclassq.video.service;

import com.iclassq.video.dto.request.branch.CreateBranchDTO;
import com.iclassq.video.dto.request.branch.UpdateBranchDTO;
import com.iclassq.video.dto.response.branch.BranchResponseDTO;

import java.util.List;

public interface BranchService {
    List<BranchResponseDTO> findAll();
    BranchResponseDTO findById(Integer id);
    BranchResponseDTO create(CreateBranchDTO dto);
    BranchResponseDTO update(Integer id, UpdateBranchDTO dto);
    void delete(Integer id);
    void activate(Integer id);
    void deactivate(Integer id);
}
