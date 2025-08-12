package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.dto.CompanyRecruitDto;
import com.festival.everyday.core.dto.FestivalDetailDto;
import com.festival.everyday.core.dto.LaborRecruitDto;
import com.festival.everyday.core.dto.RecruitStatus;
import lombok.Data;

@Data
public class FestivalDetailResponse {

    private FestivalDetailDto festivalDetailDto;

    private RecruitStatus companyRecruitStatus;
    private CompanyRecruitDto companyRecruitDto;

    private RecruitStatus laborRecruitStatus;
    private LaborRecruitDto laborRecruitDto;

    private FestivalDetailResponse(FestivalDetailDto festivalDetailDto, RecruitStatus companyRecruitStatus,
        CompanyRecruitDto companyRecruitDto, RecruitStatus laborRecruitStatus, LaborRecruitDto laborRecruitDto) {
        this.festivalDetailDto = festivalDetailDto;
        this.companyRecruitStatus = companyRecruitStatus;
        this.companyRecruitDto = companyRecruitDto;
        this.laborRecruitStatus = laborRecruitStatus;
        this.laborRecruitDto = laborRecruitDto;
    }

    public static FestivalDetailResponse of(
            FestivalDetailDto festivalDetailDto, RecruitStatus companyRecruitStatus, CompanyRecruitDto companyRecruitDto,
            RecruitStatus laborRecruitStatus, LaborRecruitDto laborRecruitDto) {
        return new FestivalDetailResponse(festivalDetailDto, companyRecruitStatus, companyRecruitDto, laborRecruitStatus, laborRecruitDto);
    }
}
