package com.festival.everyday.core.notice.domain.payload;

import com.festival.everyday.core.recruit.dto.RecruitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class RecruitDeadPayload implements NoticePayload {
    private Long festivalId;
    private String festivalName;

    public static RecruitDeadPayload from(Long festivalId, String festivalName) {
        return new RecruitDeadPayload(festivalId, festivalName);
    }
}