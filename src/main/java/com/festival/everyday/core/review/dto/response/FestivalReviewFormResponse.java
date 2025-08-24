package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.common.Mapper.ImageMapper;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.command.SimpleFestivalWithImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FestivalReviewFormResponse {

    private String festivalName;

    // address
    private AddressDto address;

    // period
    private PeriodDto period;

    // image
    private String imageUrl;

    public static FestivalReviewFormResponse from(SimpleFestivalWithImageDto dto) {
        return new FestivalReviewFormResponse(
                dto.getName(), AddressDto.of(dto.getCity(), dto.getDistrict(), dto.getDetail()),
                PeriodDto.of(dto.getBegin(), dto.getEnd()),
                ImageMapper.serverUrlToDomain(dto.getImageUrl()));
    }
}
