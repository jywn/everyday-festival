package com.festival.everyday.core.recruit.dto.command;

import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyRecruitDto {
    private List<String> extraQuestions;

    public static CompanyRecruitDto from(CompanyRecruit companyRecruit) {
        return new CompanyRecruitDto(companyRecruit.getExtraQuestions().stream().map(ExtraQuestion::getContent).toList());
    }
}
