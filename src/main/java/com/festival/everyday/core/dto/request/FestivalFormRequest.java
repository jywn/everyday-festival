package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.dto.AddressDto;
import com.festival.everyday.core.dto.PeriodDto;
import lombok.Data;

@Data
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
