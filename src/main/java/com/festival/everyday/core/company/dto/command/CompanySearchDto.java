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
    private AddressDto address;
    //private FavorStatus favorStatus;

    public static CompanySearchDto from(Company company) {
        return new CompanySearchDto(company.getId(), company.getName(), company.getCategory(), AddressDto.from(company.getAddress()));
    }
}
