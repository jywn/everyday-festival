package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.domain.Interested;
import com.festival.everyday.core.notice.handler.event.CompanyApplicationCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyApplicationCreatedDto {
    // company
    private Long companyId;
    private String companyName;

    // holder
    private Long holderId;
    // festival
    private Long festivalId;
    private String festivalName;

    public static CompanyApplicationCreatedDto from(CompanyApplicationCreatedEvent event) {
        return new CompanyApplicationCreatedDto(
                event.getCompanyId(), event.getCompanyName(),
                event.getHolderId(), event.getFestivalId(), event.getFestivalName());
    }
}
