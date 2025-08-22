package com.festival.everyday.core.application.dto.response;

import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyApplicationSimpleResponse {

    // festival
    private Long id;
    private String name;
    private String holderName;

    // period
    private PeriodDto period;

    private SELECTED selected;

    // image
    private String imageUrl;

    public static MyApplicationSimpleResponse from(MyApplicationSimpleDto myApplicationSimpleDto) {
        return new MyApplicationSimpleResponse(
                myApplicationSimpleDto.getId(), myApplicationSimpleDto.getName(), myApplicationSimpleDto.getHolderName(),
                PeriodDto.of(myApplicationSimpleDto.getBegin(), myApplicationSimpleDto.getEnd()),
                myApplicationSimpleDto.getSelected(), myApplicationSimpleDto.getImageUrl());
    }
}
