package com.festival.everyday.core.application.dto.command;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborApplicationSimpleDto {

    //application
    private Long id;
    private SELECTED selected;

    // labor
    private String name;

    // image
    private String imageUrl;
}
