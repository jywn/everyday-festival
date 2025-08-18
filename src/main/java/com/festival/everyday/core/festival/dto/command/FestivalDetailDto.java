package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivalDetailDto {

    private String name;
    private Period period;
    private AddressDto addressDto;
    private String fee;
    private String time;
    private String introduction;
    private String tel;
    private String holderName;
    private String link;
    private String imageUrl;

    public static FestivalDetailDto of(Festival festival, String imageUrl) {
        return new FestivalDetailDto(festival.getName(), festival.getPeriod(), AddressDto.from(festival.getAddress()), festival.getFee(),
                festival.getTime(), festival.getIntroduction(), festival.getTel(), festival.getHolder().getName(), festival.getLink(), imageUrl);
    }

}
