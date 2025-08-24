package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.common.Mapper.ImageMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.dto.command.SimpleCompanyWithImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;

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
                ImageMapper.serverUrlToDomain(dto.getImageUrl()
                ));
    }
}
