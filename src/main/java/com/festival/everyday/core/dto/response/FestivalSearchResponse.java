package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.dto.AddressDto;
import com.festival.everyday.core.dto.FavorStatus;
import com.festival.everyday.core.dto.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalSearchResponse {

    private Long id;
    private String name;
    private String holderName;
    private AddressDto address;
    private PeriodDto period;
}
