package com.festival.everyday.core.company.dto.command;

import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendCompanyDto {

    // company
    private Long id;
    private String name;

    // address
    private String city;
    private String district;
    private String detail;

    // category
    private Category category;

    // image
    private String imageUrl;

}
