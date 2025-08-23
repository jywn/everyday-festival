package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.common.CategoryMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class CompanyReviewFormResponse {

    // company
    private String companyName;
    private String companyCategory;

    // address
    private AddressDto address;

    public static CompanyReviewFormResponse from(Company company) {
        return new CompanyReviewFormResponse(company.getName(), CategoryMapper.enumToStr(company.getCategory()), AddressDto.from(company.getAddress()));
    }
}
