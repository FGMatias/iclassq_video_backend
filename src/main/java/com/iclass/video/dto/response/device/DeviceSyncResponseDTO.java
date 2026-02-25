package com.iclass.video.dto.response.device;

import com.iclass.video.dto.response.video.VideoSimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceSyncResponseDTO {
    private LocalDateTime serverTime;
    private List<VideoSyncInfo> playlist;
    private DeviceConfigInfo config;
    private List<EventInfo> pendingEvents;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VideoSyncInfo {
        private Integer id;
        private String name;
        private String urlVideo;
        private String thumbnail;
        private Integer duration;
        private Integer orden;
        private Long fileSize;
        private String checksum;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeviceConfigInfo {
        private Integer autoSyncInterval;
        private Boolean autoPlay;
        private Boolean loopPlayList;
        private Integer volume;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EventInfo {
        private Long eventId;
        private String eventType;
        private LocalDateTime createdAt;
        private String message;
    }
}
