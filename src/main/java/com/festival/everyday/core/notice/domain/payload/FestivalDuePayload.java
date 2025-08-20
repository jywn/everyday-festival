package com.festival.everyday.core.notice.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class FestivalDuePayload implements NoticePayload {
    private Long festivalId;
    private String festivalName;

    public static FestivalDuePayload from(Long festivalId, String festivalName) {
        return new FestivalDuePayload(festivalId, festivalName);
    }
}