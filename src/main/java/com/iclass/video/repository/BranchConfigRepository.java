package com.iclass.video.repository;

import com.iclass.video.entity.BranchConfig;
import com.iclass.video.enums.ConfigCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchConfigRepository extends JpaRepository<BranchConfig, Integer> {
    List<BranchConfig> findByBranch_IdOrderByCategoryAscDisplayOrderAsc(Integer branchId);
    List<BranchConfig> findByBranch_IdAndCategory(Integer branchId, ConfigCategory category);

    @Query("SELECT bc FROM BranchConfig bc WHERE bc.branch.id = :branchId AND bc.configKey = :configKey")
    Optional<BranchConfig> findByBranchIdAndConfigKey(Integer branchId, String configKey);
}
