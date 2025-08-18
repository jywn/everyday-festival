package com.festival.everyday.core.company.dto.response;

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
    private Category category;
    private AddressDto address;
    //private FavorStatus favorStatus;

    public static CompanySimpleResponse from(CompanySearchDto companySearchDto) {
        return new CompanySimpleResponse(companySearchDto.getId(), companySearchDto.getName(), companySearchDto.getCategory(),
                AddressDto.of(companySearchDto.getCity(), companySearchDto.getDistrict(), companySearchDto.getDetail()));
    }
}
