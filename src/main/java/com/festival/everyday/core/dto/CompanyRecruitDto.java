package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.user.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyRecruitDto {
    private PeriodDto periodDto;
    private String notice;
    private String preferred;
    private ApplyStatus applyStatus;

    public static CompanyRecruitDto from(CompanyRecruit companyRecruit, ApplyStatus applyStatus) {
        return new CompanyRecruitDto(PeriodDto.from(companyRecruit.getPeriod()), companyRecruit.getNotice(), companyRecruit.getPreferred(), applyStatus);
    }
}
