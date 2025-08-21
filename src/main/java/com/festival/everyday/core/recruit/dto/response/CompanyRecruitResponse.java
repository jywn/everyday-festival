package com.festival.everyday.core.recruit.dto.response;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitDto;
import lombok.Data;

import java.util.List;

@Data
public class CompanyRecruitResponse {
    private List<String> extraQuestions;

    private CompanyRecruitResponse(List<String> extraQuestions) {
        this.extraQuestions = extraQuestions;
    }

    public static CompanyRecruitResponse from(CompanyRecruitDto companyRecruitDto) {
        return new CompanyRecruitResponse(companyRecruitDto.getExtraQuestions());
    }
}
