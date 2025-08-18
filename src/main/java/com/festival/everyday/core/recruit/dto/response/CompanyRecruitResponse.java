package com.festival.everyday.core.recruit.dto.response;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.recruit.dto.command.CompanyRecruitDto;
import lombok.Data;

import java.util.List;

@Data
public class CompanyRecruitResponse {
    private List<Category> categories;
    private String etc;
    private String preferred;
    private String notice;

    private List<String> extraQuestions;

    private CompanyRecruitResponse(List<Category> categories, String etc, String preferred, String notice, List<String> extraQuestions) {
        this.categories = categories;
        this.etc = etc;
        this.preferred = preferred;
        this.notice = notice;
        this.extraQuestions = extraQuestions;
    }

    public static CompanyRecruitResponse from(CompanyRecruitDto companyRecruitDto) {
        return new CompanyRecruitResponse(companyRecruitDto.getCategories(), companyRecruitDto.getEtc(), companyRecruitDto.getPreferred(), companyRecruitDto.getNotice(), companyRecruitDto.getExtraQuestions());
    }
}
