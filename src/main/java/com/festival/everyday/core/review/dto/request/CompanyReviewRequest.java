package com.festival.everyday.core.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyReviewRequest {

    private Long festivalId;
    private String content;
}
