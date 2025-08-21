package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FestivalSearchResponse {

    private Long id;
    private String name;

    // holder
    private String holderName;

    // address
    private AddressDto address;

    // period
    private PeriodDto period;

    // favor
    private FavorStatus favorStatus;

    // image
    private String imageUrl;

    public static FestivalSearchResponse from(FestivalSearchDto dto) {
        return new FestivalSearchResponse(
                dto.getId(), dto.getName(), dto.getHolderName(),
                AddressDto.of(dto.getCity(), dto.getDistrict(), dto.getDetail()),
                PeriodDto.of(dto.getBegin(), dto.getEnd()),
                dto.getFavorStatus(),
                dto.getImageUrl());
    }
}
