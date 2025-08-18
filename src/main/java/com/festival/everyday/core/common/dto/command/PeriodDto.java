package com.festival.everyday.core.common.dto.command;

import com.festival.everyday.core.common.domain.Period;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PeriodDto {

    private LocalDateTime begin;
    private LocalDateTime end;

    public static PeriodDto of(LocalDateTime begin, LocalDateTime end) {
        return new PeriodDto(begin, end);
    }

    public static PeriodDto from(Period period) {
        return new PeriodDto(period.getBegin(), period.getEnd());
    }
}
