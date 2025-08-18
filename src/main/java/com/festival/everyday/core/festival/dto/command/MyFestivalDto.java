package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyFestivalDto {
    private Long id;
    private String name;

    private String city;
    private String district;
    private String detail;

    private LocalDateTime begin;
    private LocalDateTime end;

    private String imageUrl;
}
