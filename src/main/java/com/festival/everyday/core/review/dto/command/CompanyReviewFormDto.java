package com.festival.everyday.core.review.dto.command;

import com.festival.everyday.core.review.dto.request.CompanyReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyReviewFormDto {
    private Long festivalId;
    private String content;

    public static CompanyReviewFormDto from(CompanyReviewRequest companyReviewRequest) {
        return new CompanyReviewFormDto(companyReviewRequest.getFestivalId(), companyReviewRequest.getContent());
    }
}
