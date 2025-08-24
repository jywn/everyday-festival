package com.festival.everyday.core.company.dto.command;

import com.festival.everyday.core.common.Mapper.CategoryMapper;
import com.festival.everyday.core.user.domain.Category;
import lombok.Data;

@Data
public class SimpleCompanyWithImageDto {

    // company
    private String companyName;
    private String companyCategory;

    // address
    private String city;
    private String district;
    private String detail;

    //image
    private String imageUrl;

    public SimpleCompanyWithImageDto(String companyName, Category companyCategory,
                                      String city, String district, String detail,
                                      String imageUrl) {
        this.companyName = companyName;
        this.companyCategory = CategoryMapper.enumToStr(companyCategory);
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }
}
