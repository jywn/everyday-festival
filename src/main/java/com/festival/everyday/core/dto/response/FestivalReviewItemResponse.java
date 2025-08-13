package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.interaction.Review;
import com.festival.everyday.core.domain.interaction.SenderType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FestivalReviewItemResponse {

    private final Long id;
    private final Long writerId;
    private String writerName;
    private final String content;
    private final SenderType writerType;
    private final LocalDateTime createdAt;

    public static FestivalReviewItemResponse of(Review review, String writerName) {

        return FestivalReviewItemResponse.builder()
                .id(review.getId())
                .writerId(review.getSenderId())
                .writerName(writerName)
                .content(review.getContent())
                .writerType(review.getSenderType())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
