package com.festival.everyday.core.festival.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SimpleFestivalWithImageDto {

    // festival
    private String name;

    // period
    private LocalDateTime begin;
    private LocalDateTime end;

    // address
    private String city;
    private String district;
    private String detail;

    // image
    private String imageUrl;
}
