package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
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
