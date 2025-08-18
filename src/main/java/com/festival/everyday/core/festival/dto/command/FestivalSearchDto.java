package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalSearchDto {

    private Long id;
    private String name;
    private String holderName;
    private AddressDto address;
    private PeriodDto period;
    private FavorStatus favorStatus;
}
