package com.festival.everyday.core.dto.response;


import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Answer;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.application.ApplicationExtraQuestion;
import com.festival.everyday.core.domain.application.ExtraAnswer;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Labor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LaborApplicationDetailResponse {

    private final Long festivalId;
    private final String festivalName;
    private final String festivalIntroduction;

    private final Long laborId;

    private final String category;
    private final String categoryEtc;
    private final String laborName;
    private final String gender;
    private final String age;
    private final String laborTel;
    private final String laborEmail;
    private final String dueAvailable;
    private final String laborLink;

    private final List<String> questions;
    private final List<String> extraAnswers;

    public static LaborApplicationDetailResponse of(Application application) {

        Labor labor=(Labor) application.getUser();
        Festival festival=application.getFestival();
        List<Answer> answers= application.getAnswers();

        return LaborApplicationDetailResponse.builder()
                .festivalId(festival.getId())
                .festivalName(festival.getName())
                .festivalIntroduction(festival.getIntroduction())
                .laborId(labor.getId())
                .category(answers.get(0).getContent())
                .categoryEtc(answers.get(1).getContent())
                .laborName(answers.get(2).getContent())
                .gender(answers.get(3).getContent())
                .age(answers.get(4).getContent())
                .laborTel(answers.get(5).getContent())
                .laborEmail(answers.get(6).getContent())
                .dueAvailable(answers.get(7).getContent())
                .laborLink(answers.get(8).getContent())
                .questions(
                        application.getApplicationExtraQuestions().stream()
                                .map(ApplicationExtraQuestion::getExtraQuestion)
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
