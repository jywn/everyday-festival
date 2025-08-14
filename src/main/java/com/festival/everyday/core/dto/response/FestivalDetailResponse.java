package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.dto.CompanyRecruitWithApplyDto;
import com.festival.everyday.core.dto.FestivalDetailDto;
import com.festival.everyday.core.dto.LaborRecruitWithApplyDto;
import com.festival.everyday.core.dto.RecruitStatus;
import lombok.Data;

@Data
public class FestivalDetailResponse {

    private FestivalDetailDto festivalDetailDto;

    private RecruitStatus companyRecruitStatus;
    private CompanyRecruitWithApplyDto companyRecruitWithApplyDto;

    private RecruitStatus laborRecruitStatus;
    private LaborRecruitWithApplyDto laborRecruitWithApplyDto;

    private FestivalDetailResponse(FestivalDetailDto festivalDetailDto, RecruitStatus companyRecruitStatus,
                                   CompanyRecruitWithApplyDto companyRecruitWithApplyDto, RecruitStatus laborRecruitStatus, LaborRecruitWithApplyDto laborRecruitWithApplyDto) {
        this.festivalDetailDto = festivalDetailDto;
        this.companyRecruitStatus = companyRecruitStatus;
        this.companyRecruitWithApplyDto = companyRecruitWithApplyDto;
        this.laborRecruitStatus = laborRecruitStatus;
        this.laborRecruitWithApplyDto = laborRecruitWithApplyDto;
    }

    public static FestivalDetailResponse of(
            FestivalDetailDto festivalDetailDto, RecruitStatus companyRecruitStatus, CompanyRecruitWithApplyDto companyRecruitWithApplyDto,
            RecruitStatus laborRecruitStatus, LaborRecruitWithApplyDto laborRecruitWithApplyDto) {
        return new FestivalDetailResponse(festivalDetailDto, companyRecruitStatus, companyRecruitWithApplyDto, laborRecruitStatus, laborRecruitWithApplyDto);
    }
}
