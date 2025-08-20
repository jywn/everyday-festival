package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.InterestCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterestCreatedDto {

    // festival
    private Long festivalId;
    private String festivalName;

    // company
    private Long companyId;

    public static InterestCreatedDto from(InterestCreatedEvent event) {
        return new InterestCreatedDto(event.getFestivalId(), event.getFestivalName(), event.getCompanyId());
    }
}
