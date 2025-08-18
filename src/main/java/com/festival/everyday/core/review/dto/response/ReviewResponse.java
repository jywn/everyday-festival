package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {

    private final Long reviewId;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .build();
    }
}
