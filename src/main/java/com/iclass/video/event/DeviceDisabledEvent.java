package com.iclass.video.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceDisabledEvent {
    private Integer deviceId;
    private Integer userId;
}
