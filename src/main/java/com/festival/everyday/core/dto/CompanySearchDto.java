package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySearchDto {

    private Long id;
    private String name;
    private Category category;
    private AddressDto address;
    //private FavorStatus favorStatus;

    public static CompanySearchDto from(Company company) {
        return new CompanySearchDto(company.getId(), company.getName(), company.getCategory(), AddressDto.from(company.getAddress()));
    }
}
