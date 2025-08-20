package com.festival.everyday.core.notice.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyAcceptedPayload implements NoticePayload{
    private Long festivalId;
    private String festivalName;

    public static ApplyAcceptedPayload of(Long festivalId, String festivalName) {
        return new ApplyAcceptedPayload(festivalId, festivalName);
    }
}
