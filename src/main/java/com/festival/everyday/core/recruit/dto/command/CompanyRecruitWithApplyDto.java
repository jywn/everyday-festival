package com.festival.everyday.core.recruit.dto.command;

import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.application.dto.ApplyStatus;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyRecruitWithApplyDto {
    private PeriodDto periodDto;
    private String notice;
    private String preferred;
    private ApplyStatus applyStatus;

    public static CompanyRecruitWithApplyDto from(CompanyRecruit companyRecruit, ApplyStatus applyStatus) {
        return new CompanyRecruitWithApplyDto(PeriodDto.from(companyRecruit.getPeriod()), companyRecruit.getNotice(), companyRecruit.getPreferred(), applyStatus);
    }
}
