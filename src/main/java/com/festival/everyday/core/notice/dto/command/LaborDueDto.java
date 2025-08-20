package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.CompanyDueEvent;
import com.festival.everyday.core.notice.handler.event.LaborDueEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborDueDto {

    // festival
    private Long festivalId;
    private String festivalName;

    // company
    private Long laborId;

    public static LaborDueDto from(LaborDueEvent event) {
        return new LaborDueDto(event.getFestivalId(), event.getFestivalName(), event.getLaborId());
    }
}
