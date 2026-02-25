package com.iclass.video.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchConfigChangedEvent {
    private Integer branchId;
    private String configKey;
    private String oldValue;
    private String newValue;
    private Integer userId;
}
