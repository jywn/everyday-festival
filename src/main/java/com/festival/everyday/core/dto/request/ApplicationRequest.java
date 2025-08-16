package com.festival.everyday.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationRequest {

    private List<String> answers;
    private List<String> extraAnswers;
}
