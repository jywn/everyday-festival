package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LaborApplicationCreatedEvent {
    private Long laborId;
    private String laborName;

    private Long holderId;

    private Long festivalId;
    private String festivalName;

    public static LaborApplicationCreatedEvent of(Long laborId, String laborName, Long holderId, Long festivalId, String festivalName) {
        return new LaborApplicationCreatedEvent(laborId, laborName, holderId, festivalId, festivalName);
    }
}
