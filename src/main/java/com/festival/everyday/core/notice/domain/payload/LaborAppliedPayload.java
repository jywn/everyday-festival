package com.festival.everyday.core.notice.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class LaborAppliedPayload implements NoticePayload {
    // labor
    private Long laborId;
    private String laborName;

    // festival
    private Long festivalId;
    private String festivalName;

    public static LaborAppliedPayload of(Long laborId, String laborName, Long festivalId, String festivalName) {
        return new LaborAppliedPayload(laborId, laborName, festivalId, festivalName);
    }
}