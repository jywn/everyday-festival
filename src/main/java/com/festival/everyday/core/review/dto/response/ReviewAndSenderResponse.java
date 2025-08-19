package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.review.dto.command.ReviewAndSenderDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewAndSenderResponse {

    private String content;
    private String senderName;

    public static ReviewAndSenderResponse from(ReviewAndSenderDto reviewAndSenderDto) {
        return new ReviewAndSenderResponse(reviewAndSenderDto.getContent(), reviewAndSenderDto.getSenderName());
    }
}
