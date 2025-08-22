package com.festival.everyday.core.application.dto.command;

import com.festival.everyday.core.application.domain.SELECTED;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyApplicationSimpleDto {

    // application
    private Long id;

    // festival
    private Long festivalId;
    private String name;
    private String holderName;

    // period
    private LocalDateTime begin;
    private LocalDateTime end;

    private SELECTED selected;

    // image
    private String imageUrl;
}
