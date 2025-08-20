package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRecruitDeadEvent {

    // festival
    private Long festivalId;
    private String festivalName;

    // user - holder
    private Long holderId;

    public static CompanyRecruitDeadEvent of(Long festivalId, String festivalName, Long holderId) {
        return new CompanyRecruitDeadEvent(festivalId, festivalName, holderId);
    }
}
