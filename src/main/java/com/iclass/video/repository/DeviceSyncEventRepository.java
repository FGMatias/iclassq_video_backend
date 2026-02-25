package com.iclass.video.repository;

import com.iclass.video.entity.DeviceSyncEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceSyncEventRepository extends JpaRepository<DeviceSyncEvent, Long> {

    @Query("SELECT MAX(dse.event.id) FROM DeviceSyncEvent dse WHERE dse.device.id = :deviceId")
    Optional<Long> findLastProcessedEventId(@Param("deviceId") Integer deviceId);
}
