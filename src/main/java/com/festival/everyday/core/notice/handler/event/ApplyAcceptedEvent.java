package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyAcceptedEvent {
    // festival
    private Long festivalId;
    private String festivalName;

    // user
    private Long userId;
    private String userName;

    public static ApplyAcceptedEvent of(Long festivalId, String festivalName, Long userId, String userName) {
        return new ApplyAcceptedEvent(festivalId, festivalName, userId, userName);
    }
}
