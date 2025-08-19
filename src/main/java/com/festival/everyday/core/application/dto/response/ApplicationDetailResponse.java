package com.festival.everyday.core.application.dto.response;


import com.festival.everyday.core.application.dto.command.ApplicationDetailDto;
import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationDetailResponse {

    // application
    private List<String> answers;
    private List<String> extraAnswers;

    // recruit
    private List<String> extraQuestions;

    public static ApplicationDetailResponse from(ApplicationDetailDto applicationDetailDto) {
        return new ApplicationDetailResponse(
                applicationDetailDto.getAnswers(),
                applicationDetailDto.getExtraAnswers(),
                applicationDetailDto.getExtraQuestions()
        );
    }
}
