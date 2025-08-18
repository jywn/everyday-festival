package com.festival.everyday.core.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateLaborRecruitRequest {

    private LocalDateTime begin;
    private LocalDateTime end;
    private String job;
    private String wage;
    private String remark;
    private String notice;
    private List<String> extraQuestions;
}
