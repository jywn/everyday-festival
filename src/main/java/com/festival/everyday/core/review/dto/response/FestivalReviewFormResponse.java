package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class FestivalReviewFormResponse {

    private final Long festivalId;
    private final String festivalName;
    private final String holderName;
    private final LocalDateTime festivalBegin;
    private final LocalDateTime festivalEnd;

    public static FestivalReviewFormResponse of(Festival festival) {
        return FestivalReviewFormResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .holderName(festival.getHolder().getName())
                .festivalBegin(festival.getPeriod().getBegin())
                .festivalEnd(festival.getPeriod().getEnd())
                .build();
    }
}
