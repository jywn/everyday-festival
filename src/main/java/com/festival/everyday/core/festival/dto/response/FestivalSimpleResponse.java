package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalSimpleResponse {

    private Long id;

    private String name;

    private String holderName;

    private AddressDto address;

    private PeriodDto period;

    private FavorStatus favorStatus;

    private String imageUrl;

    public static FestivalSimpleResponse from(FestivalSearchDto festivalSearchDto) {
        return new FestivalSimpleResponse(
                festivalSearchDto.getId(), festivalSearchDto.getName(), festivalSearchDto.getHolderName(),
                AddressDto.of(festivalSearchDto.getCity(), festivalSearchDto.getDistrict(), festivalSearchDto.getDetail()),
                PeriodDto.of(festivalSearchDto.getBegin(), festivalSearchDto.getEnd()),
                festivalSearchDto.getFavorStatus(), festivalSearchDto.getImageUrl());
    }
}
