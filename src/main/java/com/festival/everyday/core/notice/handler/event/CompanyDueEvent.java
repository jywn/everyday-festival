package com.festival.everyday.core.notice.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDueEvent {

    // festival
    private Long festivalId;
    private String festivalName;

    // company
    private Long companyId;

    public static CompanyDueEvent from(Long festivalId, String festivalName, Long companyId) {
        return new CompanyDueEvent(festivalId, festivalName, companyId);
    }
}
