package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CompanyReviewItemResponse {

    private final Long id;
    private final Long writerId;
    private String writerName;
    private final String content;
    private final LocalDateTime createdAt;

    public static CompanyReviewItemResponse of(Review review, String writerName) {

        return CompanyReviewItemResponse.builder()
                .id(review.getId())
                .writerId(review.getSenderId())
                .writerName(writerName)
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
