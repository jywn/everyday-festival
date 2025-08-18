package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyReviewFormResponse {

    private final Long companyId;
    private final String companyName;
    private final Category companyCategory;
    private final String city;
    private final String district;
    private final String detail;

    public static CompanyReviewFormResponse of(Company company) {
        return CompanyReviewFormResponse.builder()
                .companyId(company.getId())
                .companyName(company.getName())
                .companyCategory(company.getCategory())
                .city(company.getAddress().getCity())
                .district(company.getAddress().getDistrict())
                .detail(company.getAddress().getDetail())
                .build();
    }
}
