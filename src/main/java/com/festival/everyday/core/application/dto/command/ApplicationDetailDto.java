package com.festival.everyday.core.application.dto.command;

import com.festival.everyday.core.application.domain.Answer;
import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.domain.ExtraAnswer;
import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationDetailDto {

    // application
    private List<String> answers;
    private List<String> extraAnswers;

    // recruit
    private List<String> extraQuestions;

    public static ApplicationDetailDto from(Application application) {
        return new ApplicationDetailDto(
                application.getAnswers().stream().map(Answer::getContent).toList(),
                application.getExtraAnswers().stream().map(ExtraAnswer::getContent).toList(),
                application.getRecruit().getExtraQuestions().stream().map(ExtraQuestion::getContent).toList());
    }
}
