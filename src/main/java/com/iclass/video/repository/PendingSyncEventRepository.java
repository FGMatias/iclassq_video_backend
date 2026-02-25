package com.iclass.video.repository;

import com.iclass.video.entity.PendingSyncEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PendingSyncEventRepository extends JpaRepository<PendingSyncEvent, Long> {

    @Query("SELECT e FROM PendingSyncEvent e WHERE " +
            "e.id > :lastEventId AND (" +
            "(e.targetType = 'DEVICE' AND e.targetId = :deviceId) OR " +
            "(e.targetType = 'BRANCH' AND e.targetId = :branchId) OR " +
            "(e.targetType = 'AREA' AND e.targetId = :areaId) " +
            ") ORDER BY e.id ASC")
    List<PendingSyncEvent> findPendingEventsForDevice(
            @Param("lastEventId") Long lastEventId,
            @Param("deviceId") Integer deviceId,
            @Param("branchId") Integer branchId,
            @Param("areaId") Integer areaId
    );

    void deleteByCreatedAtBefore(LocalDateTime date);
}
