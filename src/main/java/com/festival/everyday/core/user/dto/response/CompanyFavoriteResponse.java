package com.festival.everyday.core.user.dto.response;

import com.festival.everyday.core.common.Mapper.CategoryMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.Company;
import lombok.Data;

@Data
public class CompanyFavoriteResponse {
    private Long id;
    private String name;
    private AddressDto address;
    private String category;
    private String tel;
    private String link;

    private CompanyFavoriteResponse(Long id, String name, AddressDto address, String category, String tel, String link) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.tel = tel;
        this.link = link;
    }

    public static CompanyFavoriteResponse from(Company company) {
        return new CompanyFavoriteResponse(
                company.getId(),
                company.getName(),
                AddressDto.from(company.getAddress()),
                CategoryMapper.enumToStr(company.getCategory()),
                company.getTel(),
                company.getLink()
        );
    }
}