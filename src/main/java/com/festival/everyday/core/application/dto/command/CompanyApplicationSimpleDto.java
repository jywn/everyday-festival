package com.festival.everyday.core.application.dto.command;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyApplicationSimpleDto {

    //application
    private Long id;
    private SELECTED selected;

    // company
    private Long companyId;
    private String companyName;
    private Category category;

    // address
    private String city;
    private String district;
    private String detail;

    // image
    private String imageUrl;



}
