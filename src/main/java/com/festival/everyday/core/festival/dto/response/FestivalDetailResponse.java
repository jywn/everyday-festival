package com.festival.everyday.core.festival.dto.response;

import com.festival.everyday.core.recruit.dto.command.CompanyRecruitWithApplyDto;
import com.festival.everyday.core.festival.dto.command.FestivalDetailDto;
import com.festival.everyday.core.recruit.dto.command.LaborRecruitWithApplyDto;
import com.festival.everyday.core.recruit.dto.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
public class FestivalDetailResponse {

    private FestivalDetailDto festivalDetailDto;

    private RecruitStatus companyRecruitStatus;
    private CompanyRecruitWithApplyDto companyRecruitWithApplyDto;

    private RecruitStatus laborRecruitStatus;
    private LaborRecruitWithApplyDto laborRecruitWithApplyDto;

    private String imageUrl;


    public static FestivalDetailResponse of(
            FestivalDetailDto festivalDetailDto, RecruitStatus companyRecruitStatus, CompanyRecruitWithApplyDto companyRecruitWithApplyDto,
            RecruitStatus laborRecruitStatus, LaborRecruitWithApplyDto laborRecruitWithApplyDto, String imageUrl) {
        return new FestivalDetailResponse(festivalDetailDto, companyRecruitStatus, companyRecruitWithApplyDto, laborRecruitStatus, laborRecruitWithApplyDto, imageUrl);
    }
}
