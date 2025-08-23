package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.common.CategoryMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.dto.command.SimpleCompanyWithImageDto;
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

    //image
    private String imageUrl;

    public static CompanyReviewFormResponse from(SimpleCompanyWithImageDto dto) {
        return new CompanyReviewFormResponse(
                dto.getCompanyName(), dto.getCompanyCategory(),
                AddressDto.of(dto.getCity(), dto.getDistrict(), dto.getDetail()),
                dto.getImageUrl()
        );
    }
}
