package com.festival.everyday.core.company.dto.command;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySearchDto {

    private Long id;
    private String name;
    private Category category;

    // address
    private String city;
    private String district;
    private String detail;

    public static CompanySearchDto from(Company company) {
        return new CompanySearchDto(company.getId(), company.getName(), company.getCategory(), company.getAddress().getCity(), company.getAddress().getDistrict(), company.getAddress().getDetail());
    }
}
