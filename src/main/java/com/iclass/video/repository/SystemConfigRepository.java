package com.iclass.video.repository;

import com.iclass.video.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Integer> {
    Optional<SystemConfig> findByConfigKey(String configKey);

    @Query("SELECT sc FROM SystemConfig sc ORDER BY sc.displayOrder ASC")
    List<SystemConfig> findAllOrderedByDisplayOrder();
}
