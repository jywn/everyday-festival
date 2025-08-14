package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.user.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateCompanyRecruitRequest {

    private LocalDateTime begin;
    private LocalDateTime end;
    private List<Category> categories;
    private String etc;
    private String preferred;
    private String notice;
    private List<String> extraQuestions;
}
