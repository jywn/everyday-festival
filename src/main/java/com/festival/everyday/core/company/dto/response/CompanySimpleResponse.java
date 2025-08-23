package com.festival.everyday.core.company.dto.response;

import com.festival.everyday.core.common.CategoryMapper;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySimpleResponse {

    private Long id;
    private String name;
    private String category;

    private AddressDto address;

    private FavorStatus favorStatus;

    private String imageUrl;

    public static CompanySimpleResponse from(CompanySearchDto companySearchDto) {
        return new CompanySimpleResponse(companySearchDto.getId(), companySearchDto.getName(), CategoryMapper.enumToStr(companySearchDto.getCategory()),
                AddressDto.of(companySearchDto.getCity(), companySearchDto.getDistrict(), companySearchDto.getDetail()),
                companySearchDto.getFavorStatus(), companySearchDto.getImageUrl());
    }
}
