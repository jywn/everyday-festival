package com.festival.everyday.core.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyReviewListResponse {

    private final List<CompanyReviewItemResponse> reviewList;

    public static CompanyReviewListResponse from(List<CompanyReviewItemResponse> reviewList) {

        return CompanyReviewListResponse.builder()
                .reviewList(reviewList)
                .build();
    }
}
