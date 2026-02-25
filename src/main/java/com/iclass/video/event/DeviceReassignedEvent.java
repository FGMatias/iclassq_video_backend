package com.iclass.video.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceReassignedEvent {
    private Integer deviceId;
    private Integer oldAreaId;
    private Integer newAreaId;
    private Integer userId;
}
