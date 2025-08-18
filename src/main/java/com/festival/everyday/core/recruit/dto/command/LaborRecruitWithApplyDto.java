package com.festival.everyday.core.recruit.dto.command;

import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LaborRecruitWithApplyDto {
    private PeriodDto periodDto;
    private String notice;
    private String job;
    private String wage;
    private String remark;
    private ApplyStatus applyStatus;

    public static LaborRecruitWithApplyDto from(LaborRecruit laborRecruit, ApplyStatus applyStatus) {
        return new LaborRecruitWithApplyDto(
                PeriodDto.from(laborRecruit.getPeriod()),
                laborRecruit.getNotice(),
                laborRecruit.getJob(),
                laborRecruit.getWage(),
                laborRecruit.getRemark(),
                applyStatus
        );
    }
}
