package com.festival.everyday.core.company.dto.response;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDetailResponse {

    private String name;
    private Category category;
    private String introduction;
    private String ceoName;
    private String tel;
    private String email;
    private String link;

    // address
    private AddressDto addressDto;

    // favorite
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    public static CompanyDetailResponse from(CompanyDetailDto companyDetailDto) {
        return new CompanyDetailResponse(
                companyDetailDto.getName(),
                companyDetailDto.getCategory(),
                companyDetailDto.getIntroduction(),
                companyDetailDto.getCeoName(),
                companyDetailDto.getTel(),
                companyDetailDto.getEmail(),
                companyDetailDto.getLink(),
                AddressDto.of(companyDetailDto.getCity(), companyDetailDto.getDistrict(), companyDetailDto.getDetail()),
                companyDetailDto.getFavorStatus(), companyDetailDto.getImageUrl());
    }
}
