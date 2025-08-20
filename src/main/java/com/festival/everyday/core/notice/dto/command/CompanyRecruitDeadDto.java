package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.CompanyRecruitDeadEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRecruitDeadDto {

    // festival
    private Long festivalId;
    private String festivalName;

    // user - holder
    private Long holderId;

    public static CompanyRecruitDeadDto from(CompanyRecruitDeadEvent event) {
        return new CompanyRecruitDeadDto(event.getFestivalId(), event.getFestivalName(), event.getHolderId());
    }
}
