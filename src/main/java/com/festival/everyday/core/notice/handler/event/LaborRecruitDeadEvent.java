package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborRecruitDeadEvent {

    // festival
    private Long festivalId;
    private String festivalName;

    // user - holder
    private Long holderId;

    public static LaborRecruitDeadEvent of(Long festivalId, String festivalName, Long holderId) {
        return new LaborRecruitDeadEvent(festivalId, festivalName, holderId);
    }
}
