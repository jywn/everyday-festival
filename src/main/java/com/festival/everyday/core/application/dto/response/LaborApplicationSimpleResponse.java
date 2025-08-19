package com.festival.everyday.core.application.dto.response;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaborApplicationSimpleResponse {
    //application
    private Long id;
    private SELECTED selected;

    // labor
    private String name;

    // image
    private String imageUrl;

    public static LaborApplicationSimpleResponse from(LaborApplicationSimpleDto dto) {
        return new LaborApplicationSimpleResponse(dto.getId(), dto.getSelected(), dto.getName(), dto.getImageUrl());
    }
}
