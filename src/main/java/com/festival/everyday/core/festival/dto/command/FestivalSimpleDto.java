package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class FestivalSimpleDto {

    private Long id;
    private String name;
    private AddressDto address;
    private PeriodDto period;
    private FavorStatus favorStatus;

    public static FestivalSimpleDto of(Festival festival, FavorStatus favorStatus) {
        return new FestivalSimpleDto(festival.getId(), festival.getName(),
                AddressDto.from(festival.getAddress()), PeriodDto.from(festival.getPeriod()), favorStatus);
    }
}
