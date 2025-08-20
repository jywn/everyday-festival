package com.festival.everyday.core.application.dto.command;

import com.festival.everyday.core.application.domain.Answer;
import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.domain.ExtraAnswer;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.user.domain.Gender;
import com.festival.everyday.core.user.domain.Labor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LaborApplicationDetailDto {

    // application
    private List<String> answers;
    private List<String> extraAnswers;

    // recruit
    private List<String> extraQuestions;

    public static LaborApplicationDetailDto from(Application application) {
        Labor labor = (Labor) application.getUser();
        LaborRecruit recruit = (LaborRecruit) application.getRecruit();
        return new LaborApplicationDetailDto(

                application.getAnswers().stream().map(Answer::getContent).toList(),
                application.getExtraAnswers().stream().map(ExtraAnswer::getContent).toList(),
                recruit.getExtraQuestions().stream().map(ExtraQuestion::getContent).toList());
    }
}
