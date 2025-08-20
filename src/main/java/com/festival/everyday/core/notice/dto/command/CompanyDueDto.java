package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.notice.handler.event.CompanyDueEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDueDto {

    // festival
    private Long festivalId;
    private String festivalName;

    // company
    private Long companyId;

    public static CompanyDueDto from(CompanyDueEvent event) {
        return new CompanyDueDto(event.getFestivalId(), event.getFestivalName(), event.getCompanyId());
    }
}
