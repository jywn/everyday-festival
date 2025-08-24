package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.common.Mapper.ImageMapper;
import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyFestivalResponse {

    private Long id;
    private String name;
    private AddressDto address;
    private PeriodDto period;
    private String imageUrl;

    public static MyFestivalResponse from(MyFestivalDto festivalDto) {
        return new MyFestivalResponse(festivalDto.getId(), festivalDto.getName(),
                AddressDto.of(festivalDto.getCity(), festivalDto.getDistrict(), festivalDto.getName()),
                PeriodDto.of(festivalDto.getBegin(), festivalDto.getEnd()),
                ImageMapper.serverUrlToDomain(festivalDto.getImageUrl()));
    }
}
