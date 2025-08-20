package com.festival.everyday.core.notice.domain;

import com.festival.everyday.core.recruit.dto.RecruitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FestivalDeadPayload implements NoticePayload{
    private Long festivalId;
    private String festivalName;
    private RecruitType recruitType;
}