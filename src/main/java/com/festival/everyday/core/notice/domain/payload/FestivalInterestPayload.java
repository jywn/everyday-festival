package com.festival.everyday.core.notice.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class FestivalInterestPayload implements NoticePayload {
    private Long festivalId;
    private String festivalName;
}