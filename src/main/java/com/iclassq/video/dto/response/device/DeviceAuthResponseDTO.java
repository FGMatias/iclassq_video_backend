package com.iclassq.video.dto.response.device;

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
public class DeviceAuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String deviceName;
    private String deviceUsername;
    private String deviceType;
    private Integer areaId;
    private String areaName;
    private Integer branchId;
    private String branchName;
    private Integer companyId;
    private String companyName;
    private DeviceConfig config;
    private List<VideoInfo> videos;
    private LocalDateTime lastSync;

    @Data
    @Builder
    public static class DeviceConfig {
        private Integer autoSyncInterval;
        private Boolean autoPlay;
        private Boolean loopPlayList;
        private Integer volume;
    }

    @Data
    @Builder
    public static class VideoInfo {
        private Integer id;
        private String name;
        private String urlVideo;
        private String thumbnail;
        private Integer duration;
        private Integer orden;
        private Long fileSize;
        private String checksum;
    }
}
