package com.festival.everyday.core.dto.response;


import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Answer;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.application.ExtraAnswer;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.user.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CompanyApplicationDetailResponse {

    private final Long festivalId;
    private final String festivalName;
    private final String festivalIntroduction;

    private final Long companyId;

    private final String category;
    private final String categoryEtc;
    private final String companyName;
    private final String companyTel;
    private final String companyEmail;
    private final String dueAvailable;
    private final String companyLink;

    private final List<String> questions;
    private final List<String> extraAnswers;

    public static CompanyApplicationDetailResponse of(Application application) {

        Company company=(Company) application.getUser();
        Festival festival=application.getFestival();
        List<Answer> answers= application.getAnswers();

        return CompanyApplicationDetailResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .festivalIntroduction(festival.getIntroduction())
                .companyId(company.getId())
                .category(answers.get(0).getContent())
                .categoryEtc(answers.get(1).getContent())
                .companyName(answers.get(2).getContent())
                .companyTel(answers.get(3).getContent())
                .companyEmail(answers.get(4).getContent())
                .dueAvailable(answers.get(5).getContent())
                .companyLink(answers.get(6).getContent())
                .questions(
                        application.getQuestions().stream()
                                .map(ExtraQuestion::getContent)
                                .toList()
                )
                .extraAnswers(
                        application.getExtraAnswers().stream()
                                .map(ExtraAnswer::getContent)
                                .toList()
                )
                .build();


    }

}
