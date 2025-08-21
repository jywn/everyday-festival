package com.festival.everyday.core.recruit.service;

import com.festival.everyday.core.recruit.domain.Recruit;
import com.festival.everyday.core.recruit.dto.command.RecruitWithQuestionsDto;
import com.festival.everyday.core.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitQueryService {

    private final RecruitRepository recruitRepository;

    public RecruitWithQuestionsDto findRecruitWithQuestions(Long id) {
        Recruit recruitWithQuestions = recruitRepository.findRecruitWithQuestions(id);
        return RecruitWithQuestionsDto.from(recruitWithQuestions);
    }
}
