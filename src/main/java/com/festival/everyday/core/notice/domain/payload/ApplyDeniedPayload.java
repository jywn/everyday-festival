package com.festival.everyday.core.notice.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyDeniedPayload implements NoticePayload {
    private Long festivalId;
    private String festivalName;

    public static ApplyDeniedPayload of(Long festivalId, String festivalName) {
        return new ApplyDeniedPayload(festivalId, festivalName);
    }
}
