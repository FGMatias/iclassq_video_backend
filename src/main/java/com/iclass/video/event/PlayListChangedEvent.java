package com.iclass.video.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayListChangedEvent {
    private Integer areaId;
    private Integer userId;
}
