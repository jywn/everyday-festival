package com.festival.everyday.core.festival.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RecommendFestivalDto {

    // festival
    private Long id;
    private String name;

    // address
    private String city;
    private String district;
    private String detail;

    // period
    private LocalDateTime begin;
    private LocalDateTime end;

    // image
    private String imageUrl;

}
