package com.festival.everyday.core.recruit.repository;

import com.festival.everyday.core.recruit.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.festival.everyday.core.recruit.domain.QCompanyRecruit.*;
import static com.festival.everyday.core.recruit.domain.QExtraQuestion.*;
import static com.festival.everyday.core.recruit.domain.QRecruit.*;

@RequiredArgsConstructor
public class RecruitRepositoryImpl implements RecruitRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Recruit findRecruitWithQuestions(Long recruitId) {

        return queryFactory
                .select(recruit)
                .from(recruit)
                .join(recruit.extraQuestions, extraQuestion).fetchJoin()
                .where(recruit.id.eq(recruitId))
                .fetchOne();
    }
}
