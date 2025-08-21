package com.festival.everyday.core.recruit.dto.command;

import com.festival.everyday.core.recruit.domain.ExtraQuestion;
import com.festival.everyday.core.recruit.domain.Recruit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecruitWithQuestionsDto {
    private List<String> questions;

    public static RecruitWithQuestionsDto from(Recruit recruit) {
        return new RecruitWithQuestionsDto(recruit.getExtraQuestions().stream()
                .map(ExtraQuestion::getContent).toList());
    }
}
