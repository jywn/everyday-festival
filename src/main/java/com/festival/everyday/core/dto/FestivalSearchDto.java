package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.Festival;
import lombok.AccessLevel;
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
