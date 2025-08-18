package com.festival.everyday.core.festival.dto.command;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.Data;

@Data
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

    private FestivalDetailDto(String name, Period period, AddressDto addressDto, String fee, String time, String introduction, String tel, String holderName, String link) {
        this.name = name;
        this.period = period;
        this.addressDto = addressDto;
        this.fee = fee;
        this.time = time;
        this.introduction = introduction;
        this.tel = tel;
        this.holderName = holderName;
        this.link = link;

    }

    public static FestivalDetailDto from(Festival festival) {
        return new FestivalDetailDto(festival.getName(), festival.getPeriod(), AddressDto.from(festival.getAddress()), festival.getFee(),
                festival.getTime(), festival.getIntroduction(), festival.getTel(), festival.getHolder().getName(), festival.getLink());
    }

}
