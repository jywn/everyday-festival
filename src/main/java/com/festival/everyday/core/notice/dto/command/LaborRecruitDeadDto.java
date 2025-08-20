package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.LaborRecruitDeadEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborRecruitDeadDto {

    // festival
    private Long festivalId;
    private String festivalName;

    // user - holder
    private Long holderId;

    public static LaborRecruitDeadDto from(LaborRecruitDeadEvent event) {
        return new LaborRecruitDeadDto(event.getFestivalId(), event.getFestivalName(), event.getHolderId());
    }
}
