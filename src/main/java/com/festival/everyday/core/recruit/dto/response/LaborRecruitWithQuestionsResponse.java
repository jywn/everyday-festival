package com.festival.everyday.core.recruit.dto.response;

import com.festival.everyday.core.recruit.dto.command.RecruitWithQuestionsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LaborRecruitWithQuestionsResponse {
    private List<String> questions;

    public static LaborRecruitWithQuestionsResponse from(RecruitWithQuestionsDto dto) {
        return new LaborRecruitWithQuestionsResponse(dto.getQuestions());
    }
}
