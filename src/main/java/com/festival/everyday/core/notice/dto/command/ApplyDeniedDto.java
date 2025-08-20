package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.handler.event.ApplyDeniedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyDeniedDto {
    // festival
    private Long festivalId;
    private String festivalName;

    // user
    private Long userId;
    private String userName;

    public static ApplyDeniedDto from(ApplyDeniedEvent event) {
        return new ApplyDeniedDto(event.getFestivalId(), event.getFestivalName(), event.getUserId(), event.getUserName());
    }
}
