package com.festival.everyday.core.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FestivalReviewListResponse {

    private final List<FestivalReviewItemResponse> reviewList;

    public static FestivalReviewListResponse from(List<FestivalReviewItemResponse> reviewList) {
        return FestivalReviewListResponse.builder()
                .reviewList(reviewList)
                .build();
    }
}
