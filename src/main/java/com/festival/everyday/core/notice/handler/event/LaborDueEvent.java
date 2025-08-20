package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborDueEvent {

    // festival
    private Long festivalId;
    private String festivalName;

    // labor
    private Long laborId;

    public static LaborDueEvent from(Long festivalId, String festivalName, Long laborId) {
        return new LaborDueEvent(festivalId, festivalName, laborId);
    }
}
