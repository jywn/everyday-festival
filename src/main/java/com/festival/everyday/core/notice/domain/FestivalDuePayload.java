package com.festival.everyday.core.notice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FestivalDuePayload implements NoticePayload {
    private Long festivalId;
    private String festivalName;
}