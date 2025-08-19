package com.festival.everyday.core.application.dto;

import com.festival.everyday.core.user.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class companyApplicationDetailDto {

    // company
    private Category category;
    private String name;
    private String tel;
    private String email;
    private List<String> answers;
    private List<String> extraQuestions;
    private List<String> extraAnswers;

}
