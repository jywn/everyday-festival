package com.festival.everyday.core.review.dto.command;

import com.festival.everyday.core.review.dto.request.FestivalReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalReviewFormDto {
    private String content;

    public static FestivalReviewFormDto from(FestivalReviewRequest request) {
        return new FestivalReviewFormDto(request.getContent());
    }
}
