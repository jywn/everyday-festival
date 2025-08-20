package com.festival.everyday.core.festival.dto.request;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalFormRequest {

    private String name;
    private PeriodDto period;
    private AddressDto addressDto;

    private String fee;
    private String time;
    private String introduction;
    private String link;
    private String tel;
}
