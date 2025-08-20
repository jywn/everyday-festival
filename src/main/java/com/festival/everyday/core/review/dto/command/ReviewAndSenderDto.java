package com.festival.everyday.core.review.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewAndSenderDto {
    private String content;
    private String senderName;
}
