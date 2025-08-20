package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.ApplyAcceptedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyAcceptedDto {
    // festival
    private Long festivalId;
    private String festivalName;

    // user
    private Long userId;
    private String userName;

    public static ApplyAcceptedDto from(ApplyAcceptedEvent event) {
        return new ApplyAcceptedDto(event.getFestivalId(), event.getFestivalName(), event.getUserId(), event.getUserName());
    }
}
