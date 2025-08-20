package com.festival.everyday.core.notice.handler.event;

import com.festival.everyday.core.notice.domain.Interested;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyApplicationCreatedEvent {
    private Long companyId;
    private String companyName;

    private Long holderId;

    private Long festivalId;
    private String festivalName;

    public static CompanyApplicationCreatedEvent of(Long companyId, String companyName, Long holderId, Long festivalId, String festivalName) {
        return new CompanyApplicationCreatedEvent(companyId, companyName, holderId, festivalId, festivalName);
    }
}
