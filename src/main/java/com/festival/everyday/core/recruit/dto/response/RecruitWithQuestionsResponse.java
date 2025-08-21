package com.festival.everyday.core.recruit.dto.response;

import com.festival.everyday.core.recruit.dto.command.RecruitWithQuestionsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecruitWithQuestionsResponse {
    private List<String> questions;

    public static RecruitWithQuestionsResponse from(RecruitWithQuestionsDto dto) {
        return new RecruitWithQuestionsResponse(dto.getQuestions());
    }
}
