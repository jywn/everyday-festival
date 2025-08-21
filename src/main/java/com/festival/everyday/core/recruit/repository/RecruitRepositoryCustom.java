package com.festival.everyday.core.recruit.repository;

import com.festival.everyday.core.recruit.domain.Recruit;

public interface RecruitRepositoryCustom {
    Recruit findRecruitWithQuestions(Long recruitId);
}
