package com.festival.everyday.core.review.dto.response;

import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.festival.domain.Festival;
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
    private AddressDto addressDto;

    // period
    private PeriodDto periodDto;

    public static FestivalReviewFormResponse from(Festival festival) {
        return new FestivalReviewFormResponse(
                festival.getName(),
                AddressDto.from(festival.getAddress()),
                PeriodDto.of(festival.getPeriod().getBegin(), festival.getPeriod().getEnd())
        );
    }
}
