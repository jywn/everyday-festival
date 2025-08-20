package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterestCreatedEvent {

    // festival
    private Long festivalId;
    private String festivalName;

    // company
    private Long companyId;

    public static InterestCreatedEvent of(Long festivalId, String festivalName, Long companyId) {
        return new InterestCreatedEvent(festivalId, festivalName, companyId);
    }
}
