package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.CompanyApplicationCreatedEvent;
import com.festival.everyday.core.notice.handler.event.LaborApplicationCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborApplicationCreatedDto {
    // company
    private Long laborId;
    private String laborName;

    // holder
    private Long holderId;
    // festival
    private Long festivalId;
    private String festivalName;

    public static LaborApplicationCreatedDto from(LaborApplicationCreatedEvent event) {
        return new LaborApplicationCreatedDto(
                event.getLaborId(), event.getLaborName(),
                event.getHolderId(), event.getFestivalId(), event.getFestivalName());
    }
}
