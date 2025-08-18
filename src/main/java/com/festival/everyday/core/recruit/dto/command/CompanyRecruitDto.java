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

    private List<Category> categories;
    private String etc;
    private String preferred;
    private String notice;

    private List<String> extraQuestions;

    private CompanyRecruitDto(List<Category> categories, String preferred, String notice, List<String> extraQuestions) {
        this.categories = categories;
        this.preferred = preferred;
        this.notice = notice;
        this.extraQuestions = extraQuestions;
    }

    public static CompanyRecruitDto from(CompanyRecruit companyRecruit) {
        return new CompanyRecruitDto(companyRecruit.getCategories(), companyRecruit.getPreferred(), companyRecruit.getNotice(),
                companyRecruit.getExtraQuestions().stream().map(ExtraQuestion::getContent).toList());
    }
}
