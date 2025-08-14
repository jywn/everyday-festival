package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.recruit.CompanyRecruit;
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
