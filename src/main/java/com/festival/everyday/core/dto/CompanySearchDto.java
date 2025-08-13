package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.user.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySearchDto {

    private Long id;
    private String name;
    private Category category;
    private AddressDto address;
    private FavorStatus favorStatus;
}
